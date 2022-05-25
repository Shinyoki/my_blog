package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.senko.common.core.dto.ResourceDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ResourceMapper;
import com.senko.common.core.entity.ResourceEntity;
import com.senko.system.service.IResourceService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

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
