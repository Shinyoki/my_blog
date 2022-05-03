package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.senko.common.core.dto.MenuForUserDTO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.spring.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.MenuMapper;
import com.senko.common.core.entity.MenuEntity;
import com.senko.system.service.IMenuService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 菜单服务实现类
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;



    /**
     * 获取相应用户可以访问的菜单
     *
     * 又到了喜闻乐见的CRUD环节，可我还是会忘记方法的参数有啥用，这里放个教程备用
     * <a href="https://www.cnblogs.com/huanshilang/p/11985526.html">MP QueryWrapper教程</a>
     */
    @Override
    public List<MenuForUserDTO> listMenusForUser() {
        //获取当前用户
        Integer userId = SecurityUtils.getLoginUser().getUserInfoId();
        //查询可访菜单
        List<MenuEntity> poMenus = menuMapper.listMenusByUserInfoId(userId);


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
                            //理论上应该不会进入这个if分支
                            /** 该目录下没有menus 一菜单处理 */

                            //stream()操作中的流是不可逆的，迭代期间阵对流对象的修改都会引发错误，
                            //所以这里弄了个result副本，而不是直接使用curCatalog

                            //路径
                            result.setPath(curCatalog.getPath());
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
}
