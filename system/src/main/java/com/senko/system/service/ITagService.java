package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.TagBackDTO;
import com.senko.common.core.dto.TagDTO;
import com.senko.common.core.entity.TagEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.TagVO;

import java.util.List;
import java.util.Map;

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
}

