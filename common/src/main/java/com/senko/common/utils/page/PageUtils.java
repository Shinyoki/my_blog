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
     * 调用Page对象的getCurrent()方法，
     * 这里得到的current是单纯的从数字1开始，一个一个加一，也就是翻一页。
     * 用在Mapper.xml或者是selectPage()这些方法里，它会自动计算
     * Limit #{计算后的current}, #{size}作为sql的后缀。
     *
     * @return  当前页页码
     */
    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    /**
     * 获取分页总量
     *
     * 用于new Page<>(current, size)直接开启分页
     * @return 分页总量
     */
    public static Long getSize() {
        return getPage().getSize();
    }

    /**
     * 直接计算后的current，用在手动提取current size，然后手动拼接Limit sql后缀的情况
     *
     * 用于Mybatis XML中直接使用 LIMIT #{limitCurrent}, #{size}
     * @return (current - 1) * size
     * <pre>
     *     比如：
     *     (一): 前端传来current：1第一页，size：10，那么limitCurrent就是0，即从第一条数据开始查，查10条数据 ===> LIMIT 0, 10
     *     (二): 前端传来current：2第二页，size：10，那么limitCurrent就是10，即从第11条数据开始查，查10条数据 ===> LIMIT 10, 10
     * </pre>
     */
    public static Long getLimitCurrent() {
        return (getCurrent() - 1) * getSize();
    }

}
