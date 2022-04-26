package com.senko.common.utils.i18n;

import com.senko.common.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 国际化语言转换
 *
 * {@link MessageSource}：可以获取各个 {@link org.springframework.context.support.ResourceBundleMessageSource}所绑定的键值对
 * {@link LocaleContextHolder}：存储了ThreadLocal属性，可以获取相应Client的Locale
 * @author senko
 * @date 2022/4/26 9:26
 */
public class I18nUtils {

    /**
     * 转义classpath:i18n/messages_xx.properties中的国际化键值对
     * @param code
     * @param args
     * @return
     */
    public static String message(final String code, final Object... args) {
        //当前类不在Spring容器，所以使用封装好的工具类
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
