package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.TagDTO;
import com.senko.common.core.entity.TagEntity;
import com.senko.common.core.vo.ConditionVO;

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
}

