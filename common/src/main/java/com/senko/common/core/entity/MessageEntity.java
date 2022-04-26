package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 留言
 * 
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@TableName("tb_message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Integer id;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 留言内容
	 */
	private String messageContent;
	/**
	 * 用户ip
	 */
	private String ipAddress;
	/**
	 * 用户地址
	 */
	private String ipSource;
	/**
	 * 弹幕速度
	 */
	private Integer time;
	/**
	 * 是否审核
	 */
	private Integer isReview;
	/**
	 * 发布时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;

}
