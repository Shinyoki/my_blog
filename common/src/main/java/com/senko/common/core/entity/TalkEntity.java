package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 说说
 * 
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Data
@TableName("tb_talk")
public class TalkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 说说id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 说说内容
	 */
	private String content;
	/**
	 * 图片
	 */
	private String images;
	/**
	 * 是否置顶
	 */
	private Integer isTop;
	/**
	 * 状态 1.公开 2.私密
	 */
	private Integer status;
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
