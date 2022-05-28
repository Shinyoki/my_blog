package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.TagBackDTO;
import com.senko.common.common.dto.TagDTO;
import com.senko.common.common.entity.ArticleTagEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.common.vo.TagVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.ArticleTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.TagMapper;
import com.senko.common.common.entity.TagEntity;
import com.senko.system.service.ITagService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 标签服务
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements ITagService {

    private TagMapper tagMapper;

    private ArticleTagMapper articleTagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper, ArticleTagMapper articleTagMapper) {
        this.tagMapper = tagMapper;
        this.articleTagMapper = articleTagMapper;
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

    /**
     * 查询后台标签 分页集合
     * @param conditionVO   条件
     * @return              标签后台DTO 分页集合
     */
    @Override
    public PageResult<TagBackDTO> listTagBack(ConditionVO conditionVO) {
        Long count = tagMapper.selectCount(new LambdaQueryWrapper<TagEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), TagEntity::getTagName, conditionVO.getKeywords()));
        if (count == 0) {
            return new PageResult<>();
        }

        //存在相应tag
        List<TagBackDTO> tagBackDTOS = tagMapper.listTagBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<>(count.intValue(), tagBackDTOS);
    }

    /**
     * 添加或修改标签
     * @param tagVO 标签（标签名不能为空）
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateTag(TagVO tagVO) {
        TagEntity tagEntity = tagMapper.selectOne(new LambdaQueryWrapper<TagEntity>()
                .select(TagEntity::getId)
                .eq(TagEntity::getTagName, tagVO.getTagName()));
        if (Objects.nonNull(tagEntity) && tagEntity.getId().equals(tagVO.getId())) {
            throw new ServiceException("标签名已存在");
        }
        //找到名，但是id不同，则根据id修改其为新的名
        TagEntity newTag = TagEntity.builder()
                .id(tagVO.getId())
                .tagName(tagVO.getTagName())
                .build();
        this.saveOrUpdate(newTag);

    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id 集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(List<Integer> tagIdList) {
        Long count = articleTagMapper.selectCount(new LambdaQueryWrapper<ArticleTagEntity>()
                .in(ArticleTagEntity::getTagId, tagIdList));
        if (count > 0) {
            throw new ServiceException("删除失败，标签下存在文章");
        }

        tagMapper.deleteBatchIds(tagIdList);
    }
}
