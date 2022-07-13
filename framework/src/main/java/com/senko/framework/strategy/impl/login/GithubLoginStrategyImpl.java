package com.senko.framework.strategy.impl.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.senko.common.constants.SocialLoginConstants;
import com.senko.common.core.dto.GithubUserInfoDTO;
import com.senko.common.core.dto.SocialTokenDTO;
import com.senko.common.core.dto.SocialUserInfoDTO;
import com.senko.common.core.vo.GithubVO;
import com.senko.common.enums.LoginTypeEnum;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.framework.properties.GithubConfigurationProperties;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Github登录实现
 *
 * @author senko
 * @date 2022/7/12 15:01
 */
@Service("githubLoginStrategyImpl")
public class GithubLoginStrategyImpl extends AbstractSocialLoginStrategyImpl {

    @Autowired
    private GithubConfigurationProperties githubConfigurationProperties;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(GithubLoginStrategyImpl.class);

    @Override
    protected SocialTokenDTO getSocialToken(String str) {
        GithubVO githubVO = JSON.parseObject(str, GithubVO.class);
        SocialTokenDTO socialTokenDTO;
        try {

            // 获取用户Code
            String code = githubVO.getCode();
            // 获取Github用户访问令牌
            String accessToken = getAccessToken(code);
            // 获取Github用户信息
            socialTokenDTO = getGithubUserInfo(accessToken);
            logger.info("Github用户Token信息：{}", socialTokenDTO);

        } catch (ResourceAccessException e) {
            logger.error("Github获取token失败: {}", e.getMessage());
            throw new ServiceException("服务器太拉了捏，连Github又超时了，建议先换一种登录方式~");
        }

        return socialTokenDTO;
    }

    private SocialTokenDTO getGithubUserInfo(String accessToken) {
        String url = githubConfigurationProperties.getUserInfoUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + accessToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);
        String jsonResult;
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if (responseEntity.getHeaders().getContentType().includes(MediaType.TEXT_HTML)) {
                logger.error("居然获取到Github反馈的HTML网页了？是不是服务器用了什么代理？");
                throw new ServiceException("Github认证错误！");
            }
            jsonResult = responseEntity.getBody();
        } catch (RestClientException e) {
            logger.error("查询Github用户信息时出错！错误：{}", e.getMessage());
            throw new ServiceException("查询用户信息时出错");
        }

        JSONObject jsonObject = JSON.parseObject(jsonResult);
        GithubUserInfoDTO githubUserInfoDTO = new GithubUserInfoDTO(jsonObject.getInteger("id").toString(),
                accessToken,
                LoginTypeEnum.GITHUB,
                jsonObject.getString("avatar_url"),
                jsonObject.getString("login"));

        return (SocialTokenDTO) githubUserInfoDTO;
    }

    /**
     * 获取Github用户的accessToken
     */
    private String getAccessToken(String code) {
        // 获取access token
        String checkTokenUrl = githubConfigurationProperties.getCheckTokenUrl();
        LinkedMultiValueMap<String, String> requestValue = new LinkedMultiValueMap<>();
        requestValue.add(SocialLoginConstants.CLIENT_ID, githubConfigurationProperties.getClientId());
        requestValue.add(SocialLoginConstants.CLIENT_SECRET, githubConfigurationProperties.getClientSecret());
        requestValue.add(SocialLoginConstants.CODE, code);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestValue, null);

        String resultStr = restTemplate.exchange(checkTokenUrl, HttpMethod.POST, requestEntity, String.class).getBody();

        logger.info("得到用户Access Token: {}", resultStr);
        // 通过StringUtils工具类将字符串中的access_token提取出来
        String accessToken = resultStr.substring(resultStr.indexOf("access_token=") + "access_token=".length(), resultStr.indexOf("&scope="));

        return accessToken;
    }

    @Override
    protected SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialToken) {
        GithubUserInfoDTO githubUserInfoDTO = (GithubUserInfoDTO) socialToken;
        return SocialUserInfoDTO.builder()
                .nickname(githubUserInfoDTO.getNickname())
                .avatar(githubUserInfoDTO.getAvatarUrl())
                .build();

    }
}
