package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色菜单
 * 
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Data
@TableName("tb_role_menu")
public class RoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 角色id
	 */
	private Integer roleId;
	/**
	 * 菜单id
	 */
	private Integer menuId;

}
