package com.senko.common.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_article_tag")
public class ArticleTagEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 文章id
	 */
	private Integer articleId;
	/**
	 * 标签id
	 */
	private Integer tagId;

}
