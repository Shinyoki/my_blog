package com.senko.common.utils.http;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.senko.common.constants.CommonConstants;
import com.senko.common.utils.string.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端工具类
 */
public class ServletUtils
{

    /**
     * 提取请求头中的User-Agent来获取用户请求环境
     * @return
     */
    public static UserAgent getUserAgent() {
        return UserAgentUtil.parse(getRequest().getHeader("User-Agent"));
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * RequestHolder， 得到ServletRequestAttributes
     * 请求参数 可以获取 HttpServletRequest、Response、Sesion
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string)
    {
        try
        {
            //状态码
            response.setStatus(200);
            //数据类型
            response.setContentType(CommonConstants.APPLICATION_JSON);
            //响应编码方式
            response.setCharacterEncoding("utf-8");

            /**
             * write()和print()方法的区别：
             *   (1)、write()：仅支持输出字符类型数据，字符、字符数组、字符串等
             *   (2)、print()：可以将各种类型（包括Object）的数据通过默认编码转换成bytes字节形式，
             *   这些字节都通过write(int c)方法被输出
             */
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 是否是Ajax异步请求
     * 
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json"))
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
    }
}
