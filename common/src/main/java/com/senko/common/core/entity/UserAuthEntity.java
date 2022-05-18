package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

import javax.validation.constraints.Email;

/**
 * 登录用户账号信息（含密码
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user_auth")
public class UserAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id：与UserDetails保持一致
	 */
	@TableId
	private Integer id;
	/**
	 * 用户信息id
	 */
	private Integer userInfoId;
	/**
	 * 用户名：邮箱
	 */
	@Email
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 登录类型
	 * 1邮箱 2qq 3微博
	 */
	private Integer loginType;
	/**
	 * 用户登录ip
	 */
	private String ipAddress;
	/**
	 * ip来源
	 */
	private String ipSource;
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
	/**
	 * 上次登录时间
	 */
	private LocalDateTime lastLoginTime;

}
