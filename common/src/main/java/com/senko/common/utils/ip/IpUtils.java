package com.senko.common.utils.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.senko.common.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

/**
 * IP解析工具类
 *
 * @author senko
 * @date 2022/4/27 20:06
 */
public class IpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtils.class);

    private static final String baseUrl = "http://opendata.baidu.com/api.php";

    /**
     * 得到IP地址所对应的 地理信息 和 网络运营商
     * @param ipAddress  127.0.0.1
     * @return
     */
    public static String getIpSource(String ipAddress) {
        StringBuilder queryPath = new StringBuilder("query=")
                .append(ipAddress)
                .append("&resource_id=6006&oe=utf8");

        String result = HttpUtils.sendGetRequest(baseUrl, queryPath.toString(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(result);
        String location = "";

        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            if (key.equals("data")) {
                JSONArray dataArray = (JSONArray) entry.getValue();
                if (StringUtils.isNotEmpty(dataArray)) {
                    JSONObject dataObject = (JSONObject) dataArray.get(0);
                    location = dataObject.getString("location");
                }
            }
        }
        location = StringUtils.isBlank(location) ? "未知" : location;
        LOGGER.info("得到IP解析地址：{}", location);
        return location;
    }

    /**
     * 解析请求中的IP地址
     * @param request
     * @return
     */
    public static String getIpAddressFromRequest(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    //不太建议用这种方式于生产环境，虚拟网卡vpn和linux的/etc/hosts都会导致获取的不如人意
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 获取IpSource来源
     * @param request
     * @return
     */
    public String getIpSource(HttpServletRequest request) {
        return getIpSource(getIpAddressFromRequest(request));
    }

}
