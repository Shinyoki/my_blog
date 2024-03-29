package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色可访资源
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_role_resource")
public class RoleResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 角色id
	 */
	private Integer roleId;
	/**
	 * 权限id
	 */
	private Integer resourceId;

}
