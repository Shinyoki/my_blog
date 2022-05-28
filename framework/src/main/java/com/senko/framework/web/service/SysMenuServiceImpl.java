package com.senko.framework.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.common.dto.ElementTreeLabelOptionDTO;
import com.senko.common.core.dto.MenuDTO;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.core.entity.RoleMenuEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.MenuVO;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.MenuMapper;
import com.senko.common.core.entity.MenuEntity;
import com.senko.system.service.IMenuService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 菜单服务实现类
 */
@Service("menuService")
public class SysMenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    /**
     * 获取登录用户的 可选菜单
     * 第一层元素都是目录，children都是菜单
     * @return
     */
    @Override
    public List<MenuForUserDTO> listMenusForUser() {
        //获取当前用户
        Integer userInfoId = SecurityUtils.getLoginUser().getUserInfoId();
        return listMenusForUser(userInfoId);
    }

    /**
     * 获取 相应用户 可以访问的菜单
     * 第一层元素都是目录，children都是菜单
     *
     * 又到了喜闻乐见的CRUD环节，可我还是会忘记方法的参数有啥用，这里放个教程备用
     * <a href="https://www.cnblogs.com/huanshilang/p/11985526.html">MP QueryWrapper教程</a>
     */
    @Override
    public List<MenuForUserDTO> listMenusForUser(Integer userInfoId) {

        //查询可访菜单
        List<MenuEntity> poMenus = menuMapper.listMenusByUserInfoId(userInfoId);


        /**
         * 和Ruoyi的Menus一样，只是目录与菜单并没有以独立的字段相区分，
         * 在本项目中判断目录与菜单主要看parentId是否为null，是的话就是目录
         */
        //目录
        List<MenuEntity> catalogs = listOfCatalog(poMenus);
        //子菜单
        Map<Integer, List<MenuEntity>> menus = mapOfMenus(poMenus);

        //构造成DTO格式
        return convertMenuPO2MenuForUserDTO(catalogs, menus);
    }

    /**
     * 查询菜单后台
     * @param conditionVO   条件 菜单名
     * @return              菜单后台DTO 集合
     */
    @Override
    public List<MenuDTO> listMenusBack(ConditionVO conditionVO) {
        //查询菜单
        List<MenuEntity> menuEntities = menuMapper.selectList(new LambdaQueryWrapper<MenuEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), MenuEntity::getName, conditionVO.getKeywords()));
        //目录
        List<MenuEntity> catalogList = listOfCatalog(menuEntities);
        //子菜单
        Map<Integer, List<MenuEntity>> menusMap = mapOfMenus(menuEntities);

        //转换为MenuDTO
        return convertMenuPO2MenuDTO(catalogList, menusMap);
    }

    /**
     * 根据菜单ID 删除其还有子菜单
     * @param menuId        菜单ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenuByMenuId(Integer menuId) {
        //查看菜单是否与 角色 进行了绑定
        Long count = roleMenuMapper.selectCount(new LambdaQueryWrapper<RoleMenuEntity>()
                .eq(RoleMenuEntity::getMenuId, menuId));
        if (count > 0) {
            throw new ServiceException("该菜单存在角色与之绑定，无法删除");
        }

        //查询子菜单
        List<MenuEntity> childrenMenus = menuMapper.selectList(new LambdaQueryWrapper<MenuEntity>()
                .select(MenuEntity::getId)
                .eq(MenuEntity::getParentId, menuId));
        List<Integer> childMenusIdList = childrenMenus.stream()
                .map(MenuEntity::getId)
                .collect(Collectors.toList());

        //连同menuID和它的孩子，一起删除
        childMenusIdList.add(menuId);
        this.removeBatchByIds(childMenusIdList);
    }

    /**
     * 获取菜单图标集合
     * @return      图标名 集合
     */
    @Override
    public List<String> listMenuIcons() {
        return menuMapper.selectList(new QueryWrapper<MenuEntity>().select("DISTINCT icon")).stream()
                        .map(MenuEntity::getIcon)
                        .collect(Collectors.toList());
    }

    /**
     * 更新或新增菜单
     * @param menuVO    菜单表单
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenu(MenuVO menuVO) {
        MenuEntity menuEntity = BeanCopyUtils.copyObject(menuVO, MenuEntity.class);
        this.saveOrUpdate(menuEntity);
    }

    /**
     * 查询 角色-菜单 集合，并返回符合ElementUI Tree所需data结构的数据
     */
    @Override
    public List<ElementTreeLabelOptionDTO> listRoleMenuOption() {
        //查询所有 菜单 id、parentId、orderNum
        List<MenuEntity> menus = menuMapper.selectList(new LambdaQueryWrapper<MenuEntity>()
                .select(MenuEntity::getId,MenuEntity::getName, MenuEntity::getParentId, MenuEntity::getOrderNum));

        // 目录
        List<MenuEntity> catalogs = listOfCatalog(menus);
        // 子菜单
        Map<Integer, List<MenuEntity>> childrenMap = mapOfMenus(menus);

        //PO转DTO
        return catalogs.stream()
                .map(curCatalog -> {
                    List<ElementTreeLabelOptionDTO> labelChildrenList = new LinkedList<>();
                    List<MenuEntity> childrenMenus = childrenMap.get(curCatalog.getId());

                    //当前目录有孩子
                    if (CollectionUtils.isNotEmpty(childrenMenus)) {
                        //构筑labelOptionDTO的孩子
                        labelChildrenList = childrenMenus.stream()
                                .sorted(Comparator.comparing(MenuEntity::getOrderNum))
                                .map(curMenu -> {
                                    //塑形DTO
                                    return ElementTreeLabelOptionDTO.builder()
                                            .id(curMenu.getId())
                                            .label(curMenu.getName())
                                            .build();
                                })
                                .collect(Collectors.toList());
                    }

                    //完成DTO
                    return ElementTreeLabelOptionDTO.builder()
                            .id(curCatalog.getId())
                            .label(curCatalog.getName())
                            .children(labelChildrenList)
                            .build();
                })
                .collect(Collectors.toList());
    }


    /**
     * 根据parentId为null就是目录的特征去分辨出目录
     *
     * @param menus     Menus PO
     * @return          Catalog Menus
     */
    private List<MenuEntity> listOfCatalog(List<MenuEntity> menus) {
        return menus.stream()
                //根据parentId为null就是目录的特征去分辨出 目录
                .filter(e -> Objects.isNull(e.getParentId()))
                //排序
                .sorted(Comparator.comparing(MenuEntity::getOrderNum))
                .collect(Collectors.toList());
    }

    /**
     * 根据parentId不是null就是菜单的特征去分辨菜单
     *
     * 得到以parentId为key，同父id的MenuEntity集合为值的map
     *
     * ====================================
     * Collectors.groupingBy(objClass::getFunction)
     * 会将getFunction得到的值作为map的key，同key的obj作为map的value
     * List<MenuEntity>虽然是集合，但是getFunction可能得到相同的值，此时重复的key就会把相应的obj添加到集合中
     * 很像Mysql的GROUP BY啊。
     * ====================================
     * <pre>
     *
     *     TUser {
     *      String name;
     *      Integet age;
     *
     *      Getter & Setter...
     *     }
     *
     *         LinkedList<TUser> list = new LinkedList<>(Arrays.asList(new TUser("s3-1", 3),new TUser("s2", 2), new TUser("s1", 1), new TUser("s3-2", 3)));
     *         Map<Integer, List<TUser>> collect = list.stream().collect(Collectors.groupingBy(TUser::getAge));
     *         for (Map.Entry<Integer, List<TUser>> entry : collect.entrySet()) {
     *             System.out.println("key：" + entry.getKey());
     *             for (TUser tUser : entry.getValue()) {
     *                 System.out.println(tUser);
     *             }
     *         }
     *
     * 最终结果
     * map {
     *     1 : [ TUser{name='s1', age=1} ],
     *     2 : [ TUser{name='s2', age=2} ],
     *     3 : [ TUser{name='s3-1', age=3}, TUser{name='s3-2', age=3} ]
     * }
     *
     * </pre>
     *
     * @param menus     Menus PO
     * @return          Menus
     */
    private Map<Integer, List<MenuEntity>> mapOfMenus(List<MenuEntity> menus) {
        return menus.stream()
                //根据parentId不是null就是菜单的特征去分辨 菜单
                .filter(e->Objects.nonNull(e.getParentId()))
                //菜单不需要排序
                //Collectors.groupingBy 父ID为键，同父id的MenuEntity集合为值的map
                .collect(Collectors.groupingBy(MenuEntity::getParentId));
    }

    /**
     * 将目录和菜单转换成 MenuForUserDTO
     * @param catalogs      目录
     * @param menus         不带目录的菜单
     * @return              Menus For User DTO
     */
    private List<MenuForUserDTO> convertMenuPO2MenuForUserDTO(List<MenuEntity> catalogs, Map<Integer, List<MenuEntity>> menus) {
        //便利目录
        return catalogs.stream()
                .map(new Function<MenuEntity, MenuForUserDTO>() {
                    /**
                     * 将 目录 和 他的children 塑形为{@link MenuForUserDTO}
                     *
                     * @param curCatalog 当前迭代 目录 流所对应的MenuEntity
                     * @return 最终stream().map()塑形的结果
                     */
                    @Override
                    public MenuForUserDTO apply(MenuEntity curCatalog) {
                        //最终return的
                        MenuForUserDTO result = new MenuForUserDTO();
                        List<MenuForUserDTO> resultChildren = new ArrayList<>();

                        //得到同父id的菜单集合
                        List<MenuEntity> childrenMenus = menus.get(curCatalog.getId());

                        //检查当前目录有没有子菜单
                        if (CollectionUtils.isNotEmpty(childrenMenus)) {
                            /** 该目录下有menus 多级菜单处理 */
                            //拷贝迭代MenuEntity对象的属性到MenuForDTO中:能跨类复制同名字段真是流批，没想到反射还能这样用
                            //目录和他的children
                            result = BeanCopyUtils.copyObject(curCatalog, MenuForUserDTO.class);
                            resultChildren = childrenMenus.stream()
                                    //排个序
                                    .sorted(Comparator.comparing(MenuEntity::getOrderNum))
                                    //塑形：应该用递归实现子节点PO2DTO转换的，还好没有深度为3的菜单，先将就着用吧
                                    .map(new Function<MenuEntity, MenuForUserDTO>() {
                                        @Override
                                        public MenuForUserDTO apply(MenuEntity curChildMenu) {
                                            MenuForUserDTO dto = BeanCopyUtils.copyObject(curChildMenu, MenuForUserDTO.class);
                                            //字段名和属性不一样了，重新设置下
                                            dto.setHidden(curChildMenu.getIsHidden() == 1);
                                            return dto;
                                        }
                                    })
                                    .collect(Collectors.toList());
                        } else {
                            /** 该目录下没有menus 单集菜单处理 */
                            /**
                             * 部分目录不存在子菜单，于是将自己作为子菜单赋值....
                             * 以后可能会重写这块逻辑
                             *
                             * {
                             *             "name": null,
                             *             "path": "/setting",
                             *             "component": "/setting/Setting.vue",
                             *             "icon": null,
                             *             "hidden": false,
                             *             "children": [
                             *                 {
                             *                     "name": "个人中心",
                             *                     "path": "",
                             *                     "component": "/setting/Setting.vue",
                             *                     "icon": "el-icon-myuser",
                             *                     "hidden": null,
                             *                     "children": null
                             *                 }
                             *             ]
                             * }
                             */
                            //stream()操作中的流是不可逆的，迭代期间阵对流对象的修改都会引发错误，
                            //所以这里弄了个result副本，而不是直接使用curCatalog

                            //路径
                            result.setPath(curCatalog.getPath());
                            //不设置目录的名是为了防止和children重名，导致vue router duplicate
                            //目录所属组件
                            result.setComponent(curCatalog.getComponent());
                            resultChildren.add(MenuForUserDTO.builder()
                                    .path("")
                                    .name(curCatalog.getName())
                                    .icon(curCatalog.getIcon())
                                    .component(curCatalog.getComponent())
                                    .build()
                            );
                        }
                        result.setChildren(resultChildren);
                        result.setHidden(curCatalog.getIsHidden() == 1);

                        return result;
                    }
                }).collect(Collectors.toList());
    }


    /**
     * 将目录和菜单转换成 MenuForUserDTO
     * @param catalogList      目录
     * @param menusMap         不带目录的菜单
     * @return                 {@link MenuDTO} 后台菜单
     */
    private List<MenuDTO> convertMenuPO2MenuDTO(List<MenuEntity> catalogList, Map<Integer, List<MenuEntity>> menusMap) {
        return catalogList.stream()
                .map(catalog -> {
                    //目录
                    MenuDTO catalogMenuDTO = BeanCopyUtils.copyObject(catalog, MenuDTO.class);
                    //子菜单
                    List<MenuDTO> menuDTOList = BeanCopyUtils.copyList(menusMap.get(catalog.getId()), MenuDTO.class).stream()
                            .sorted(Comparator.comparing(MenuDTO::getOrderNum))
                            .collect(Collectors.toList());
                    //绑定
                    catalogMenuDTO.setChildren(menuDTOList);
                    return catalogMenuDTO;
                })
                //排序
                .sorted(Comparator.comparing(MenuDTO::getOrderNum))
                .collect(Collectors.toList());
    }
}
