package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.common.dto.CommentDTO;
import com.senko.common.common.dto.ReplyCountDTO;
import com.senko.common.common.dto.ReplyDTO;
import com.senko.common.common.vo.CommentVO;
import com.senko.common.common.vo.WebsiteConfigVO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.CommentBackDTO;
import com.senko.common.common.vo.CommentIsReviewVO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.web.HTMLUtils;
import com.senko.system.service.IWebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.CommentMapper;
import com.senko.common.common.entity.CommentEntity;
import com.senko.system.service.ICommentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    /**
     * 查询评论后台DTO 分页集合
     * @param conditionVO   条件
     * @return              评论后台DTO 分页集合
     */
    @Override
    public PageResult<CommentBackDTO> listCommentBack(ConditionVO conditionVO) {
        Long count = commentMapper.selectCountByConditionVO(conditionVO);
        if (count == 0) {
            return new PageResult<>();
        }

        List<CommentBackDTO> commentBackDTOList = commentMapper.listCommentBack(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<CommentBackDTO>(count.intValue(),commentBackDTOList);
    }

    /**
     * 修改评论的审核状态
     * @param isReviewVO    评论id集合、需要被改为的状态
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommentsIsReview(CommentIsReviewVO isReviewVO) {
        List<CommentEntity> comments = isReviewVO.getIdList().stream().map(id -> {
                    return CommentEntity.builder()
                            .id(id)
                            .isReview(isReviewVO.getIsReview())
                            .build();
                })
                .collect(Collectors.toList());
        this.updateBatchById(comments);
    }


    /**
     * 查询评论
     */
    @Override
    public PageResult<CommentDTO> listComments(CommentVO commentVO) {
        // 查询评论数量
        Long commentCount = commentMapper.selectCount(new LambdaQueryWrapper<CommentEntity>()
                // 筛选：文章id
                .eq(Objects.nonNull(commentVO.getTopicId()), CommentEntity::getTopicId, commentVO.getTopicId())
                // 筛选：评论类型
                .eq(CommentEntity::getType, commentVO.getType())
                // 没有父id即为一级评论
                .isNull(CommentEntity::getParentId)
                // 审核状态：通过
                .eq(CommentEntity::getIsReview, 1)
        );
        if (commentCount == 0) {
            return new PageResult<>();
        }

        // 查询评论
        List<CommentDTO> commentDTOList = commentMapper.listComments(PageUtils.getLimitCurrent(), PageUtils.getSize(), commentVO);
        if (CollectionUtils.isEmpty(commentDTOList)) {
            return new PageResult<>();
        }

        //查询点赞数
        Map<String, Object> likeCountMap = redisHandler.hGetAll(RedisConstants.COMMENT_LIKE_COUNT_TAG);

        // 提取评论的id为集合
        List<Integer> commentIdList = commentDTOList.stream()
                .map(CommentDTO::getId)
                .collect(Collectors.toList());

        // 根据一级评论搜索回复
        List<ReplyDTO> replyDTOS =  commentMapper.listReplies(commentIdList);

        // 设置每条回复的点赞数
        replyDTOS.forEach(replyDTO -> {
                    replyDTO.setLikeCount(
                            (Integer) likeCountMap.get(replyDTO.getId().toString())
                    );
                });

        // 根据id分组
        Map<Integer, List<ReplyDTO>> replyMap = replyDTOS.stream()
                .collect(Collectors.groupingBy(ReplyDTO::getParentId));

        // 恩据评论id查询回复量
        Map<Integer, Integer> commentIdReplyCountMap = commentMapper.listReplyCountByCommentId(commentIdList).stream()
                .collect(Collectors.toMap(ReplyCountDTO::getCommentId, ReplyCountDTO::getReplyCount));

        // 整合
        commentDTOList.forEach(commentDTO -> {
            commentDTO.setLikeCount((Integer) likeCountMap.get(commentDTO.getId().toString()));
            commentDTO.setReplyCount(commentIdReplyCountMap.get(commentDTO.getId()));
            commentDTO.setReplyDTOList(replyMap.get(commentDTO.getId()));
        });
        return new PageResult<>(commentCount.intValue(), commentDTOList);
    }

    /**
     * 添加评论
     */
    @Override
    public void saveComment(CommentVO commentVO) {
        // 判断是否需要审核
        WebsiteConfigVO websiteConfig = websiteConfigService.getWebsiteConfig();
        Integer isReview = websiteConfig.getIsCommentReview();

        // 过滤HTML Script标签
        commentVO.setCommentContent(HTMLUtils.filter(commentVO.getCommentContent()));

        CommentEntity newComment = CommentEntity.builder()
                .userId(SecurityUtils.getLoginUser().getUserInfoId())
                .replyUserId(commentVO.getReplyUserId())
                .topicId(commentVO.getTopicId())
                .commentContent(commentVO.getCommentContent())
                .parentId(commentVO.getParentId())
                .type(commentVO.getType())
                .isReview(isReview == CommonConstants.TRUE ? 0 : 1) // 需要审核则设置为0（未审核），不需要审核则设置为1（已审核）
                .build();
        commentMapper.insert(newComment);

        // 如果网站开启了邮箱通知，则发送邮件通知
        if (websiteConfig.getIsEmailNotice() == CommonConstants.TRUE) {
            CompletableFuture.runAsync(() -> {
                // 发送邮件通知
                emailNotice(newComment);
            });
        }
    }

    /**
     * 给评论点赞
     */
    @Override
    public void likeComment(Integer commentId) {
        String redisKey = RedisConstants.COMMENT_USER_LIKE + SecurityUtils.getLoginUser().getUserInfoId();
        if (redisHandler.sIsMember(redisKey, commentId)) {
            // 如果玩家的点赞列表中存在该评论，则取消点赞
            redisHandler.sRemove(redisKey, commentId);
            // 减少点赞数
            redisHandler.hDecrement(RedisConstants.COMMENT_LIKE_COUNT_TAG, commentId.toString(), 1L);
        } else {
            redisHandler.sAdd(redisKey, commentId);
            // 增加点赞数
            redisHandler.hIncrement(RedisConstants.COMMENT_LIKE_COUNT_TAG, commentId.toString(), 1L);
        }
    }

    /**
     * 给reply用户发送邮件通知
     */
    public void emailNotice(CommentEntity comment) {
        // TODO 邮箱通知
    }


}
