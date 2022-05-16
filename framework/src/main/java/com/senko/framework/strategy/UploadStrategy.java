package com.senko.framework.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传策略
 * @author senko
 * @date 2022/5/16 11:00
 */
public interface UploadStrategy {

    /**
     * 上传文件
     * @param file  文件
     * @param path  上传路径
     * @return      文件地址
     */
    String uploadFile(MultipartFile file, String path);
}
