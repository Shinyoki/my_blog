package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.dto.TagDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.TagMapper;
import com.senko.common.core.entity.TagEntity;
import com.senko.system.service.ITagService;

import java.util.List;

/**
 * 标签服务
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements ITagService {

    private TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    /**
     * 通过ConditionVO查询标签
     * @param conditionVO   条件
     * @return              标签DTO 集合
     */
    @Override
    public List<TagDTO> listTagsBySearch(ConditionVO conditionVO) {
        LambdaQueryWrapper<TagEntity> query = new LambdaQueryWrapper<TagEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), TagEntity::getTagName, conditionVO.getKeywords())
                .orderByDesc(TagEntity::getId);
        return BeanCopyUtils.copyList(tagMapper.selectList(query), TagDTO.class);
    }
}
