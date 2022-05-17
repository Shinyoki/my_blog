package com.senko.framework.strategy.impl.upload;

import com.senko.common.exceptions.service.UtilsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Objects;

/**
 * 本地上传策略
 *
 * 需要防火墙开放对应端口，并使用nginx转发
 * 比如本机的公网ip为225.6.6.6，nginx监听转发端口为83
 * <pre>
 *     application.yml 配置：
 *     #上传模式
 *      upload:
 *        #上传模式 [local | oss]
 *        mode: local
 *        #本地模式
 *        local:
 *          #服务器地址
 *             url: http://localhost:83/
 *          #文件路径
 *             path: /usr/local/upload/
 *
 *     nginx配置：
 *     server {
 *       #监听83端口
 *       listen 83;
 *       #主机ip
 *       server_name 225.6.6.6;
 *       location / {
 *              #文件存放处
 *               root mouwenjianjia;
 *             }
 *     }
 * </pre>
 * @author senko
 * @date 2022/5/16 14:19
 */
@Service("localUploadStrategyImpl")
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl{


    /**
     * 文件基本路径
     */
    @Value("${upload.local.path}")
    private String basePath;

    /**
     * 文件服务器url
     */
    @Value("${upload.local.url}")
    private String baseUrl;

    /**
     * 判断相应路径是否存在文件
     * @param filePath  文件路径
     * @return          文件是否存在
     */
    @Override
    public Boolean exists(String filePath) {
        return new File(basePath + filePath).exists();
    }

    /**
     * 上传
     * @param path          文件路径    articles/
     * @param fileName      文件名     abc.doc
     * @param inputStream   输入流
     * @throws IOException
     */
    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException {
        //判断目录是否存在 or else 创建目录
        File directory = new File(basePath + path);
        if (!directory.exists()) {
            //不存在则创建
            if (!directory.mkdirs()) {
                //创建返回值非true
                throw new UtilsException(basePath + path + "目录创建失败");
            }
        }

        //写入文件
        File file = new File(basePath + path + fileName);
        //只有文件不存在时创建并写入
        if (file.createNewFile()) {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(inputStream);
                bos = new BufferedOutputStream(new FileOutputStream(file));

                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, length);
                }

                bos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
                if (Objects.nonNull(bis)) {
                    bis.close();
                }
                if (Objects.nonNull(bos)) {
                    bos.close();
                }
            }
        }
    }

    /**
     * url地址资源
     * @param filePath  文件路径
     * @return
     */
    @Override
    public String getFileAccessUrl(String filePath) {
        return baseUrl + filePath;
    }
}
