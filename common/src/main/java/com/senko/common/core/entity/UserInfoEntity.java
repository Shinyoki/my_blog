package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息（不含密码
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Data
@TableName("tb_user_info")
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Integer id;
	/**
	 * 邮箱号
	 */
	private String email;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 用户简介
	 */
	private String intro;
	/**
	 * 个人网站
	 */
	private String webSite;
	/**
	 * 是否禁用 0:FALSE 1:TRUE
	 */
	private Integer isDisable;
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
