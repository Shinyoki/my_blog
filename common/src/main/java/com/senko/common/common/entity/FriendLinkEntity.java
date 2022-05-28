package com.senko.common.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 友链、外站
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_friend_link")
public class FriendLinkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 链接名
	 */
	private String linkName;
	/**
	 * 链接头像
	 */
	private String linkAvatar;
	/**
	 * 链接地址
	 */
	private String linkAddress;
	/**
	 * 链接介绍
	 */
	private String linkIntro;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateTime;

}
