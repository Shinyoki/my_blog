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
	private Date createTime;
	/**
	 * 更新时间
	 */
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
