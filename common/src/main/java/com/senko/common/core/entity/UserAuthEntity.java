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
 * @date 2022-04-24 16:39:55
 */
@Data
@TableName("tb_user_auth")
public class UserAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户信息id
	 */
	private Integer userInfoId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 登录类型
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
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;

}
