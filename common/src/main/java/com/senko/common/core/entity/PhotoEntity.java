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
 * 照片
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_photo")
public class PhotoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 相册id
	 */
	private Integer albumId;
	/**
	 * 照片名
	 */
	private String photoName;
	/**
	 * 照片描述
	 */
	private String photoDesc;
	/**
	 * 照片地址
	 */
	private String photoSrc;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
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
