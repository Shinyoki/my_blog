package com.senko.framework.strategy.impl.upload;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * OSS对象存储 上传策略
 * TODO Oss对象存储
 * @author senko
 * @date 2022/5/16 20:55
 */
@Service("ossUploadStrategyImpl")
public class OssUploadStrategyImpl extends AbstractUploadStrategyImpl{

    /**
     * 判断相应路径是否存在文件
     * @param filePath  文件路径
     * @return          是否存在
     */
    @Override
    public Boolean exists(String filePath) {
        return null;
    }

    /**
     * 上传
     * @param path          文件路径
     * @param fileName      文件名
     * @param inputStream   输入流
     * @throws IOException  IO异常
     */
    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException {

    }

    /**
     * 获取文件访问URL
     * @param filePath  文件路径
     * @return          文件路径
     */
    @Override
    public String getFileAccessUrl(String filePath) {
        return null;
    }
}
