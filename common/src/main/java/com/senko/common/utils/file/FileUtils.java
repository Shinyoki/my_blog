package com.senko.common.utils.file;

import com.senko.common.utils.stream.StreamUtils;
import com.senko.common.utils.string.StringUtils;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 文件MD5工具类
 *
 * 基于Spring MessageDigest utils再次封装的md5校验工具
 * 使用DigestUtils的方法加密的结果与messageDigest的方法加密结果一致
 * https://blog.csdn.net/wo240/article/details/80969226
 * @author senko
 * @date 2022/5/16 11:27
 */
public class FileUtils {

    /**
     * 获取文件md5值
     * @param inputStream   文件流
     * @return               md5值
     */
    public static String getMd5(InputStream inputStream) {
        String s = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StreamUtils.readAllAsBytes(inputStream));
             s = new String(Hex.encodeHex(md5.digest()));
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            StreamUtils.closeQuietly(inputStream);
        }
        return s;
    }

    /**
     * 截取文件拓展名
     * @param fileName 文件名      abc.doc
     * @return         文件拓展名   .doc
     */
    public static String getExtName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    /**
     * 将MultipartFile转换为普通file
     * @param multipartFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            String filename = multipartFile.getOriginalFilename();
            String[] filenames = Objects.requireNonNull(filename).split("\\.");
            file = File.createTempFile(filenames[0], filenames[1]);
            multipartFile.transferTo(file);

            //最终删除临时文件
            file.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }



}
