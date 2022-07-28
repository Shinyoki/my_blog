package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.TagCountDTO;
import com.senko.common.core.PageResult;
import com.senko.common.common.dto.TagBackDTO;
import com.senko.common.common.dto.TagDTO;
import com.senko.common.common.entity.TagEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.common.vo.TagVO;

import java.util.List;

/**
 * 标签服务
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface ITagService extends IService<TagEntity> {


    /**
     * 通过ConditionVO查询标签
     * @param conditionVO   条件
     * @return              标签DTO 集合
     */
    List<TagDTO> listTagsBySearch(ConditionVO conditionVO);

    /**
     * 查询后台标签 分页集合
     * @param conditionVO   条件
     * @return              标签后台DTO 分页集合
     */
    PageResult<TagBackDTO> listTagBack(ConditionVO conditionVO);

    /**
     * 添加或修改标签
     * @param tagVO 标签（标签名不能为空）
     */
    void saveOrUpdateTag(TagVO tagVO);

    /**
     * 删除标签
     *
     * @param tagIdList 标签id 集合
     */
    void deleteTag(List<Integer> tagIdList);

    /**
     * 查询标签分类数量
     * @return 标签分类数量
     */
    PageResult<TagCountDTO> listTagCount();

}

