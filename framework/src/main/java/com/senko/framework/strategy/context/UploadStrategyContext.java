package com.senko.framework.strategy.context;

import com.senko.common.enums.UploadModeEnum;
import com.senko.framework.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传策略 上下文
 *
 * https://www.programminghunter.com/article/65372237814/
 * @author senko
 * @date 2022/5/16 9:15
 */
@Component
public class UploadStrategyContext {

    @Value("${upload.mode}")
    private String uploadMode;

    /**
     * 策略桶
     * 由于@Service的存在，被@Autowire标注的map会自动搜索
     * 符合的UploadStrategy Bean，并以bean name作为map的key
     * map : {
     *     key: strategy name
     *     value: strategyImpl
     * }
     */
    @Autowired
    public Map<String, UploadStrategy> uploadStrategyMap;

    /**
     * 上传文件
     * @param file  文件
     * @param path  上传地址
     * @return      文件路径
     */
    public String executeUploadFile(MultipartFile file, String path) {
        return uploadStrategyMap.get(UploadModeEnum.getStrategy(uploadMode))
                .uploadFile(file, path);
    }
}
