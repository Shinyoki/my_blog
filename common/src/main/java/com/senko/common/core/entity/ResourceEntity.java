package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资源(权限相关)
 * 具体GET/POST/UPDATE/PUT操作
 * 
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@TableName("tb_resource")
public class ResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 资源名
	 */
	private String resourceName;
	/**
	 * 权限路径
	 */
	private String url;
	/**
	 * 请求方式
	 */
	private String requestMethod;
	/**
	 * 父权限id   所属模块
	 */
	private Integer parentId;
	/**
	 * 是否匿名访问 0否 1是
	 */
	private Integer isAnonymous;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;

}
