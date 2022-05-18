package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 页面
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_page")
public class PageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 页面id
	 */
	@TableId
	private Integer id;
	/**
	 * 页面名
	 */
	private String pageName;
	/**
	 * 页面标签
	 */
	private String pageLabel;
	/**
	 * 页面封面
	 */
	private String pageCover;
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
