package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志记录
 * 
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@TableName("tb_operation_log")
public class OperationLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Integer id;
	/**
	 * 操作模块
	 */
	private String optModule;
	/**
	 * 操作类型
	 */
	private String optType;
	/**
	 * 操作url
	 */
	private String optUrl;
	/**
	 * 操作方法
	 */
	private String optMethod;
	/**
	 * 操作描述
	 */
	private String optDesc;
	/**
	 * 请求参数
	 */
	private String requestParam;
	/**
	 * 请求方式
	 */
	private String requestMethod;
	/**
	 * 返回数据
	 */
	private String responseData;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 操作ip
	 */
	private String ipAddress;
	/**
	 * 操作地址
	 */
	private String ipSource;
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

}
