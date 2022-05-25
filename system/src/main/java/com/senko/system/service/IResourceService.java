package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.ResourceDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.vo.ConditionVO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IResourceService extends IService<ResourceEntity> {

    /**
     * 获取后台资源列集合
     * <pre>
     * [
     *  模块1: {
     *      xxx...
     *      childList
     *  },
     *  模块2: {
     *      xxx...
     *      childList
     *  },
     *  ...
     * ]
     * </pre>
     * @param conditionVO       查询条件
     * @return                  资源列表
     */
    List<ResourceDTO> listResourcesBack(ConditionVO conditionVO);
}

