package com.senko.common.utils.ip;

import com.alibaba.fastjson.JSON;
import com.senko.common.utils.string.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;


/**
 * Http 工具类
 *
 * @author senko
 * @date 2022/4/27 20:06
 */
public class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 模拟发送get请求
     * @param url           url地址  http(s)://ip:port/resourcepath
     * @param paramStr      附带参数  name=sun&age=21&id=1
     * @param charset       字符集名  utf-8
     * @return              响应json字符串
     */
    public static String sendGetRequest(String url, String paramStr, String charset) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        String requestUrl = "";
        try {
            requestUrl = StringUtils.isNotBlank(paramStr) ? url + "?" + paramStr : url;

            LOGGER.info("发送Get请求：{}", requestUrl);

            //可关闭的Client
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(requestUrl);
            //请求头参数
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("connection", "Keep-Alive");
            httpGet.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            //结果
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);

            //流操作
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }

            LOGGER.info("发送Get请求，得到响应：{}", sb.toString());

            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送Get请求出错：{}", requestUrl);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    /**
     * 模拟发送post请求
     * 以表单的形式
     * @param url       url地址             http(s)://ip:port/resourcepath
     * @param params    模拟form表单元素      Map {name:sun,age=21,id=1}
     * @param charset   字符集               utf-8
     * @return          JSON响应
     */
    public static String sendPostRequestInForm(String url, final Map<String, String> params, String charset) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            LOGGER.info("发送Post请求：{}", url);

            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            //headers
            httpPost.setHeader("accept", "*/*");
            httpPost.setHeader("connection", "Keep-Alive");
            httpPost.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpPost.setHeader("Accept-Charset", "utf-8");
            httpPost.setHeader("contentType", "utf-8");

            //模拟表单内容
            if (null != params || params.size() != 0) {
                /**
                 * 模拟表单内容
                 * application/x-www-form-urlencoded
                 * 需传入HttpEntity，可使用UrlEncodedFormEntity实现类
                 * UrlEncoded的构造函数，需要传入一个以BasicNameValuePair键值对作为元素的集合，
                 * 还有指定字符集
                 */
                LinkedList<BasicNameValuePair> paramList = new LinkedList<>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(postEntity);
            }

            //结果
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }

            LOGGER.info("发送Post请求，得到响应：{}", sb.toString());

            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送Post请求异常：{}", url);
        } finally {
            try {
                if (StringUtils.isNotNull(reader)) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 模拟发送post请求
     * 以JSON
     * @param url       url地址             http(s)://ip:port/resourcepath
     * @param params    模拟form表单元素      Map {name:sun,age=21,id=1}
     * @param charset   字符集               utf-8
     * @return          JSON响应
     */
    public static String sendPostRequestInJson(String url, final Map<String, Object> params, String charset) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            LOGGER.info("发送Post请求：{}", url);

            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            //headers
            httpPost.setHeader("accept", "*/*");
            httpPost.setHeader("connection", "Keep-Alive");
            httpPost.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpPost.setHeader("Accept-Charset", "utf-8");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

            //模拟表单内容
            if (null != params || params.size() != 0) {
                String json = JSON.toJSONString(params);
                /**
                 * 模拟表单内容
                 * application/json;charset=UTF-8
                 * 需传入HttpEntity，可使用StringEntity
                 */
                StringEntity entity = new StringEntity(json);
                entity.setContentEncoding(charset);
                entity.setContentType("application/json;charset=UTF-8");
                httpPost.setEntity(entity);
            }

            //结果
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), charset));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }

            LOGGER.info("发送Post请求，得到响应：{}", sb.toString());

            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送Post请求异常：{}", url);
        } finally {
            try {
                if (StringUtils.isNotNull(reader)) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
