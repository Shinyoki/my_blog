package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Data
@TableName("tb_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 评论用户Id
	 */
	private Integer userId;
	/**
	 * 评论主题id
	 */
	private Integer topicId;
	/**
	 * 评论内容
	 */
	private String commentContent;
	/**
	 * 回复用户id
	 */
	private Integer replyUserId;
	/**
	 * 父评论id
	 */
	private Integer parentId;
	/**
	 * 评论类型 1.文章 2.友链 3.说说
	 */
	private Integer type;
	/**
	 * 是否删除  0否 1是
	 */
	private Integer isDelete;
	/**
	 * 是否审核
	 */
	private Integer isReview;
	/**
	 * 评论时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
