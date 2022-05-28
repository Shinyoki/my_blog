package com.senko.framework.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.common.dto.ElementTreeLabelOptionDTO;
import com.senko.common.core.dto.ResourceDTO;
import com.senko.common.core.entity.RoleResourceEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.ResourceVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.framework.config.security.manager.FilterInvocationSecurityMetadataSourceImpl;
import com.senko.system.mapper.RoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ResourceMapper;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.system.service.IResourceService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 资源服务
 */
@Service("resourceService")
public class SysResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

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
    @Override
    public List<ResourceDTO> listResourcesBack(ConditionVO conditionVO) {
        //查询 资源PO
        List<ResourceEntity> rawResources = resourceMapper.selectList(new LambdaQueryWrapper<ResourceEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), ResourceEntity::getResourceName, conditionVO.getKeywords()));
        //提取 模块
        List<ResourceEntity> parentResourceList = listOfResourceModule(rawResources);
        //提取 资源
        Map<Integer, List<ResourceEntity>> childrenResourceMap = mapOfChildrenResource(rawResources);

        //加工
        return parentResourceList.stream()
                .map(parentResourceEntity -> {
                    ResourceDTO parentDTO = BeanCopyUtils.copyObject(parentResourceEntity, ResourceDTO.class);
                    List<ResourceDTO> childrenDTOList = BeanCopyUtils.copyList(childrenResourceMap.get(parentResourceEntity.getId()), ResourceDTO.class);

                    parentDTO.setChildren(childrenDTOList);
                    return parentDTO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 新增或修改资源
     * @param resourceVO    资源信息
     */
    @Override
    public void saveOrUpdateResource(ResourceVO resourceVO) {
        ResourceEntity resourceEntity = BeanCopyUtils.copyObject(resourceVO, ResourceEntity.class);
        //根据id是否存在，存在->新增，不存在->修改
        this.saveOrUpdate(resourceEntity);

        //更新资源所需权限后需要刷新权限缓存
        filterInvocationSecurityMetadataSource.clearResourcesCache();
    }

    /**
     * 删除资源
     * @param resourceId    资源ID
     */
    @Override
    public void deleteResource(Integer resourceId) {
        //查看与该resource绑定的角色
        Long count = roleResourceMapper.selectCount(new LambdaQueryWrapper<RoleResourceEntity>()
                .eq(RoleResourceEntity::getResourceId, resourceId));
        if (count > 0) {
            throw new ServiceException("删除失败，该资源下存在角色");
        }

        //如果是模块还需要删除子资源
        List<Integer> childResourceIdList = resourceMapper.selectList(new LambdaQueryWrapper<ResourceEntity>()
                        .select(ResourceEntity::getId)
                        .eq(ResourceEntity::getParentId, resourceId))
                .stream()
                .map(ResourceEntity::getId)
                .collect(Collectors.toList());

        childResourceIdList.add(resourceId);

        //删除
        this.removeBatchByIds(childResourceIdList);
    }

    /**
     * 查询 角色-资源 集合
     * @return 角色-资源 集合：并返回符合ElementUI Tree所需data结构的数据
     */
    @Override
    public List<ElementTreeLabelOptionDTO> listRoleResourceOption() {
        //查询所有资源
        List<ResourceEntity> resources = resourceMapper.selectList(new LambdaQueryWrapper<ResourceEntity>()
                .select(ResourceEntity::getId, ResourceEntity::getResourceName, ResourceEntity::getParentId));
        // 目录(模块)
        List<ResourceEntity> moduleList = listOfResourceModule(resources);
        // 子资源
        Map<Integer, List<ResourceEntity>> childrenMap = mapOfChildrenResource(resources);

        //PO转DTO
        return moduleList.stream()
                .map(curModule -> {
                    List<ElementTreeLabelOptionDTO> labelChildrenList = new LinkedList<>();
                    List<ResourceEntity> childrenResourceList = childrenMap.get(curModule.getId());

                    //模块下存在子资源
                    if (CollectionUtils.isNotEmpty(childrenMap)) {
                        labelChildrenList = childrenResourceList.stream()
                                .map(curResource -> {
                                    return ElementTreeLabelOptionDTO.builder()
                                            .id(curResource.getId())
                                            .label(curResource.getResourceName())
                                            .build();
                                })
                                .collect(Collectors.toList());
                    }

                    //塑形DTO
                    return ElementTreeLabelOptionDTO.builder()
                            .id(curModule.getId())
                            .label(curModule.getResourceName())
                            .children(labelChildrenList)
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * 加工为Map集合
     *
     * resource和menu的处理逻辑都是一样的，把没有parentId的作为“一级菜单”，有parentId的作为"二级菜单"
     * 所有的层级关系只有2层的深度，所以可以用这种偷懒的方式简单实现，不用考虑使用递归
     *
     * Map {
     *     parentId1: [childrenList...],
     *     parentId2: [childrenList...],
     * }
     * @param rawResources      资源PO
     * @return                  Map{parentId: childrenList}
     */
    private Map<Integer, List<ResourceEntity>> mapOfChildrenResource(List<ResourceEntity> rawResources) {
        return rawResources.stream()
                .filter(resourceEntity -> Objects.nonNull(resourceEntity.getParentId()))
                .collect(Collectors.groupingBy(ResourceEntity::getParentId));
    }

    /**
     * 提取出 模块
     *
     * @param rawResources  资源PO
     * @return              XXX模块
     */
    private List<ResourceEntity> listOfResourceModule(List<ResourceEntity> rawResources) {
        return rawResources.stream()
                .filter(resourceEntity -> Objects.isNull(resourceEntity.getParentId()))
                .collect(Collectors.toList());
    }
}
