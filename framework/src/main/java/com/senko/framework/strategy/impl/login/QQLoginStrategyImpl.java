package com.senko.framework.strategy.impl.login;

import com.alibaba.fastjson.JSON;
import com.senko.common.constants.SocialLoginConstants;
import com.senko.common.core.dto.QQTokenDTO;
import com.senko.common.core.dto.QQUserInfoDTO;
import com.senko.common.core.dto.SocialTokenDTO;
import com.senko.common.core.dto.SocialUserInfoDTO;
import com.senko.common.core.vo.QQLoginVO;
import com.senko.common.enums.LoginTypeEnum;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.string.StringUtils;
import com.senko.framework.properties.QQConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * qq登录实现
 * @author senko
 * @date 2022/7/12 7:49
 */
@Service("qqLoginStrategyImpl")
public class QQLoginStrategyImpl extends AbstractSocialLoginStrategyImpl{

    @Autowired
    private QQConfigurationProperties qqConfigurationProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected SocialTokenDTO getSocialToken(String str) {
        QQLoginVO qqLoginVO = JSON.parseObject(str, QQLoginVO.class);
        // 校验QQ token
        checkQQToken(qqLoginVO);
        // 返回token信息
        return SocialTokenDTO.builder()
                .openId(qqLoginVO.getOpenId())
                .accessToken(qqLoginVO.getAccessToken())
                .loginType(LoginTypeEnum.QQ.getType())
                .build();
    }

    /**
     * 校验QQ token
     */
    private void checkQQToken(QQLoginVO qqLoginVO) {
        Map<String, String> qqData = new HashMap<>(1);
        // accessToken
        qqData.put(SocialLoginConstants.ACCESS_TOKEN, qqLoginVO.getAccessToken());
        try {
            // 请求得到结果
            String jsonResult = restTemplate.getForObject(qqConfigurationProperties.getCheckTokenUrl(), String.class, qqData);
            QQTokenDTO qqTokenDTO = JSON.parseObject(StringUtils.getBracketsContent(Objects.requireNonNull(jsonResult)), QQTokenDTO.class);

            // 校验结果
            if (!qqTokenDTO.getOpenid().equals(qqLoginVO.getOpenId())) {
                throw new ServiceException("QQ登录失败！");
            }
        } catch (Exception e) {
            throw new ServiceException("QQ登录失败！");
        }
    }

    @Override
    protected SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialToken) {
        // 定义请求参数
        Map<String, String> qqData = new HashMap<>(3);
        // accessToken
        qqData.put(SocialLoginConstants.ACCESS_TOKEN, socialToken.getAccessToken());
        // openId
        qqData.put(SocialLoginConstants.OPEN_ID, socialToken.getOpenId());
        // app id
        qqData.put(SocialLoginConstants.OAUTH_CONSUMER_KEY, qqConfigurationProperties.getAppId());

        // 请求得到结果
        String jsonResult = restTemplate.getForObject(qqConfigurationProperties.getUserInfoUrl(), String.class, qqData);
        QQUserInfoDTO socialUserInfoDTO = JSON.parseObject(Objects.requireNonNull(jsonResult), QQUserInfoDTO.class);

        return SocialUserInfoDTO.builder()
                .nickname(Objects.requireNonNull(socialUserInfoDTO).getNickname())
                .avatar(socialUserInfoDTO.getAvatar())
                .build();
    }
}
