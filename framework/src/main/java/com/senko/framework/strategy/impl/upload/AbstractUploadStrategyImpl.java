package com.senko.framework.strategy.impl.upload;

import com.senko.common.exceptions.service.UtilsException;
import com.senko.common.utils.file.FileUtils;
import com.senko.framework.strategy.UploadStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽象上传 模板
 *
 * @author senko
 * @date 2022/5/16 11:21
 */
@Service
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    /**
     * 判断相应路径是否存在文件
     * @param filePath  文件路径
     * @return          存在文件与否
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     * @param path          文件路径
     * @param fileName      文件名
     * @param inputStream   输入流
     * @throws IOException  IO异常
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问URL
     * @param filePath  文件路径
     * @return          文件URL路径
     */
    public abstract String getFileAccessUrl(String filePath);

    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            String fileMd5 = FileUtils.getMd5(file.getInputStream());
            String extName = FileUtils.getExtName(file.getOriginalFilename());
            //new name: md5.extName
            String finalName = fileMd5 + extName;

            if (!exists(finalName)) {
                //不存在：上传
                upload(path, finalName, file.getInputStream());
            }
            //搜索对应的URL
            return getFileAccessUrl(path + finalName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UtilsException("文件上传失败");
        }
    }
}
