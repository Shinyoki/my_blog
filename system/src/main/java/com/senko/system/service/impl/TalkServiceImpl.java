package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.CommentCountDTO;
import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.common.dto.TalkDTO;
import com.senko.common.common.entity.TalkEntity;
import com.senko.common.common.vo.TalkVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.web.HTMLUtils;
import com.senko.system.mapper.CommentMapper;
import com.senko.system.mapper.TalkMapper;
import com.senko.system.service.ITalkService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 说说
 */
@Service("talkService")
public class TalkServiceImpl extends ServiceImpl<TalkMapper, TalkEntity> implements ITalkService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(TalkServiceImpl.class);

    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisHandler redisHandler;

    /**
     * 查询后台说说  分页集合
     *
     * @param conditionVO 条件
     * @return 后台说说 分页集合
     */
    @Override
    public PageResult<TalkBackDTO> listTalkBack(ConditionVO conditionVO) {
        Long count = talkMapper.selectCount(new LambdaQueryWrapper<TalkEntity>()
                .eq(Objects.nonNull(conditionVO.getStatus()), TalkEntity::getStatus, conditionVO.getStatus()));
        if (count == 0) {
            return new PageResult<>();
        }

        List<TalkBackDTO> talkBackDTOList = talkMapper.listTalkBack(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        //转换imgList
        talkBackDTOList.forEach(talkBackDTO -> {
            if (Objects.nonNull(talkBackDTO.getImages())) {
                //解析并转换
                talkBackDTO.setImgList(JSON.parseObject(talkBackDTO.getImages(), List.class));
            }
        });
        return new PageResult<>(count.intValue(), talkBackDTOList);
    }

    /**
     * 根据talkId查询后台说说
     *
     * @param talkId talkId
     * @return 后台说说
     */
    @Override
    public TalkBackDTO getTalkBackById(Integer talkId) {
        return talkMapper.getTalkBackById(talkId);
    }

    /**
     * 新增后台说说
     *
     * @param talkVO 说说id、说说内容、说说图片、说说状态、置顶状态
     */
    @Override
    public void saveOrUpdateTalk(TalkVO talkVO) {
        TalkEntity talkEntity = JSON.parseObject(JSON.toJSONString(talkVO), TalkEntity.class);
        talkEntity.setUserId(SecurityUtils.getLoginUser().getUserInfoId());
        this.saveOrUpdate(talkEntity);
    }

    /**
     * 获取主页说说内容
     */
    @Override
    public List<String> listHomeTalks() {
        // 获取最新的10条
        List<String> collect = talkMapper.selectList(new LambdaQueryWrapper<TalkEntity>()
                        .eq(TalkEntity::getStatus, CommonConstants.TRUE)
                        .orderByDesc(TalkEntity::getIsTop)
                        .last("limit 10"))
                .stream()
                .map(talkEntity -> {
                    String result = null;
                    // 内容长度超过200字符
                    if (talkEntity.getContent().length() > 200) {
                        // 清除script style标签，并截取200字符
                        result = HTMLUtils.deleteHMTLTag(talkEntity.getContent())
                                .substring(0, 200);
                    } else {
                        // 清除script style标签
                        result = HTMLUtils.filter(talkEntity.getContent());
                    }
                    return result;
                })
                .collect(Collectors.toList());

        return collect;
    }


    /**
     * 查看说说
     */
    @Override
    public PageResult<TalkDTO> listTalks() {
        Long aLong = talkMapper.selectCount(new LambdaQueryWrapper<TalkEntity>()
                .eq(TalkEntity::getStatus, 1));
        if (aLong == 0) {
            return new PageResult<>();
        }

        List<TalkDTO> talkDTOList = talkMapper.listTalks(PageUtils.getCurrent(), PageUtils.getSize());


        // 查询每个说说的评论
        List<Integer> idList = talkDTOList.stream()
                .map(TalkDTO::getId)
                .collect(Collectors.toList());

        List<CommentCountDTO> commentCountList = commentMapper.selectCountOfCommentByTalkIds(idList);
        Map<Integer, Integer> commentIdCountMap = commentCountList.stream()
                .collect(Collectors.toMap(CommentCountDTO::getId, CommentCountDTO::getCommentCount));

        // 查询点赞量
        Map<String, Object> talkLikeMap = redisHandler.hGetAll(RedisConstants.TALK_LIKE_COUNT_TAG);

        talkDTOList.forEach(talkDTO -> {
            if (Objects.nonNull(talkDTO.getImages())) {
                //解析并转换
                talkDTO.setImgList(JSON.parseObject(talkDTO.getImages(), List.class));
            }
            talkDTO.setCommentCount(commentIdCountMap.get(talkDTO.getId()));
            talkDTO.setLikeCount((Integer) talkLikeMap.get(talkDTO.getId().toString()));
        });
        return new PageResult<>(aLong.intValue(), talkDTOList);
    }

    @Override
    public TalkDTO getTalkById(Integer talkId) {
        TalkDTO talkDTO = talkMapper.getTalkById(talkId);
        if (Objects.isNull(talkDTO)) {
            throw new ServiceException("该说说不存在！");
        }
        Object o = redisHandler.hGet(RedisConstants.TALK_LIKE_COUNT_TAG, talkId.toString());
        Optional.ofNullable(o).ifPresent(o1 -> talkDTO.setLikeCount((Integer) o1));
        talkDTO.setImgList(JSON.parseObject(talkDTO.getImages(), List.class));
        return talkDTO;
    }


    @Override
    public void likeTalk(Integer talkId) {
        String redisTag = RedisConstants.TALK_USER_LIKE + SecurityUtils.getLoginUser().getUserInfoId().toString();
        if (redisHandler.sIsMember(redisTag, talkId)) {
            // 用户点赞集合里存在该说说，则取消点赞
            redisHandler.sRemove(redisTag, talkId);
            redisHandler.hDecrement(RedisConstants.TALK_LIKE_COUNT_TAG, talkId.toString(), 1);
        } else {
            // 用户点赞集合里不存在该说说，则点赞
            redisHandler.sAdd(redisTag, talkId);
            redisHandler.hIncrement(RedisConstants.TALK_LIKE_COUNT_TAG, talkId.toString(), 1);
        }
    }
}
