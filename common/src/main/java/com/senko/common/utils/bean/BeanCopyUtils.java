package com.senko.common.utils.bean;


import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean 克隆工具类
 *
 * 使用spring的BeanUtils
 * 貌似commons-beanutils中的装换不支持java.util.Date
 * @author senko
 * @date 2022/4/26 15:16
 */
public class BeanCopyUtils {
    /**
     * 复制对象，返回一个非空Target.class的实例
     *
     * 可以不同源，但是两个类的属性名要一致
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object source, Class<T> target) {
        T result = null;
        try {
            //防止target == null
             result = target.newInstance();
            if (StringUtils.isNotNull(source)) {
                //复制
                BeanUtils.copyProperties(source, result);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 复制集合，返回非空的集合对象
     * @param source        源
     * @param target        目标元素类型
     * @param <TARGET>      目标内对象类型
     * @param <SOURCE>      源集合内对象类型
     * @return              Copy后的集合
     */
    public static <SOURCE, TARGET> List<TARGET> copyList(List<SOURCE> source, Class<TARGET> target) {
        ArrayList<TARGET> result = new ArrayList<>();

        if (StringUtils.isNotNull(source) && source.size() > 0) {
            //源不为空、数量大于0
            for (SOURCE sourceItem : source) {
                result.add(copyObject(sourceItem, target));
            }
        }
       //异常被copyObject()捕获处理了
        return result;
    }
}
