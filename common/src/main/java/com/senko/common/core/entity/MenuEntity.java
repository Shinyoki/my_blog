package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 可访菜单，不包含完整路径，只对菜单做分类划分
 *
 * 具体GET/POST/UPDATE/PUT操作归类于ResourceEntity
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@TableName("tb_menu")
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 菜单名
	 */
	private String name;
	/**
	 * 菜单路径
	 */
	private String path;
	/**
	 * 组件
	 */
	private String component;
	/**
	 * 菜单icon
	 */
	private String icon;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 父id
	 */
	private Integer parentId;
	/**
	 * 是否隐藏  0否1是
	 */
	private Integer isHidden;

}
