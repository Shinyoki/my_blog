package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.ElementTreeLabelOptionDTO;
import com.senko.common.core.dto.ResourceDTO;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.ResourceVO;

import java.util.List;
import java.util.Map;

/**
 * 资源Service
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

    /**
     * 新增或修改资源
     * @param resourceVO    资源信息
     */
    void saveOrUpdateResource(ResourceVO resourceVO);

    /**
     * 删除资源
     * @param resourceId    资源ID
     */
    void deleteResource(Integer resourceId);

    /**
     * 查询 角色-资源 集合
     * @return 角色-资源 集合：并返回符合ElementUI Tree所需data结构的数据
     */
    List<ElementTreeLabelOptionDTO> listRoleResourceOption();
}

