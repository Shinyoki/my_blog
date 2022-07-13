package com.senko.controller.test;

import com.alibaba.fastjson.JSON;
import com.senko.framework.properties.GithubConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author senko
 * @date 2022/7/12 15:55
 */
@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GithubConfigurationProperties githubConfigurationProperties;

    public void getGithubUserInfo() {
        String url = githubConfigurationProperties.getUserInfoUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + "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");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);

        String result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class).getBody();
        System.out.println("得到用户信息");
        System.out.println(result);
    }

    public void test() {
        String api = "https://sessionserver.mojang.com/session/minecraft/profile/";
        String uuid = "9536bebdbe6746f28e906e0a0a582f24";
        String url = api + uuid;

        String str = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();

        PlayerInfo playerInfo = JSON.parseObject(str, PlayerInfo.class);
        System.out.println("解析后的玩家");
        System.out.println(playerInfo);
    }
}


class PlayerInfo {
    @Override
    public String toString() {
        return "PlayerInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", properties=" + JSON.toJSONString(properties) +
                '}';
    }

    private String id;
    private String name;
    private List<Properties> properties;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    static class Properties {
        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}
