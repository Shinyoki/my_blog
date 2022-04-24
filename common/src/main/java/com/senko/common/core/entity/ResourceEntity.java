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
	 * 父权限id
	 */
	private Integer parentId;
	/**
	 * 是否匿名访问 0否 1是
	 */
	private Integer isAnonymous;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
