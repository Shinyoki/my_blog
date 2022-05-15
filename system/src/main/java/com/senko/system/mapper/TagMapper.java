package com.senko.system.mapper;

import com.senko.common.core.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {

    /**
     * 以articleId查找该文章所绑定的tag标签
     * @param articleId  文章id
     * @return           与该文章绑定的tags
     */
    List<String> listTagNameByArticleId(Integer articleId);
}
