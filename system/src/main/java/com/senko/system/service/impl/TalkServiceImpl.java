package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.common.vo.TalkVO;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.spring.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.TalkMapper;
import com.senko.common.common.entity.TalkEntity;
import com.senko.system.service.ITalkService;

import java.util.List;
import java.util.Objects;

/**
 * 说说
 */
@Service("talkService")
public class TalkServiceImpl extends ServiceImpl<TalkMapper, TalkEntity> implements ITalkService {

    @Autowired
    private TalkMapper talkMapper;

    /**
     * 查询后台说说  分页集合
     * @param conditionVO   条件
     * @return              后台说说 分页集合
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
        return new PageResult<>(count.intValue(),talkBackDTOList);
    }

    /**
     * 根据talkId查询后台说说
     * @param talkId        talkId
     * @return              后台说说
     */
    @Override
    public TalkBackDTO getTalkBackById(Integer talkId) {
        return talkMapper.getTalkBackById(talkId);
    }

    /**
     * 新增后台说说
     * @param talkVO    说说id、说说内容、说说图片、说说状态、置顶状态
     */
    @Override
    public void saveOrUpdateTalk(TalkVO talkVO) {
        TalkEntity talkEntity = JSON.parseObject(JSON.toJSONString(talkVO), TalkEntity.class);
        talkEntity.setUserId(SecurityUtils.getLoginUser().getUserInfoId());
        this.saveOrUpdate(talkEntity);
    }
}
