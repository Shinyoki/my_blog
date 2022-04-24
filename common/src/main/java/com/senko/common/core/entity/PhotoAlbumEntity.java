package com.senko.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 相册
 * 
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Data
@TableName("tb_photo_album")
public class PhotoAlbumEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 相册名
	 */
	private String albumName;
	/**
	 * 相册描述
	 */
	private String albumDesc;
	/**
	 * 相册封面
	 */
	private String albumCover;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
	/**
	 * 状态值 1公开 2私密
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
