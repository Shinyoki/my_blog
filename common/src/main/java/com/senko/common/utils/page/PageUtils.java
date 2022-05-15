package com.senko.common.utils.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senko.common.utils.string.StringUtils;

/**
 * 分页工具类
 *
 * @author senko
 * @date 2022/5/15 14:38
 */
public class PageUtils {

    //当前线程(请求)的Page
    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    // ===============setter & getter==============

    /**
     * 为当前线程设置分页模型
     * @param page  分页模型
     */
    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    /**
     * 获取当前线程的Page，如果没有则返回new Page<>()
     * @return 分页模型
     */
    public static Page<?> getPage() {
        Page<?> resultPage = PAGE_HOLDER.get();
        if (StringUtils.isNull(resultPage)) {
            //如果是空，考虑到ThreadLocal的get特性，需要覆盖初始值
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    /**
     * 删除该线程的Page
     */
    public static void remove() {
        PAGE_HOLDER.remove();
    }

    /**
     * 获取当前页页码
     * @return  当前页页码
     */
    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    /**
     * 获取分页总量
     * @return 分页总量
     */
    public static Long getSize() {
        return getPage().getSize();
    }

    /**
     * 以Current页码计算的limit
     * current: [1 ~ 00]，所以对应Page的下标就是 current - 1
     * @return (current - 1) * size
     */
    public static Long getLimitCurrent() {
        return (getCurrent() - 1) * getSize();
    }

}
