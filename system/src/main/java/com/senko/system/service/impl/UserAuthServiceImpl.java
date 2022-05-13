package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.dto.UserAreaDTO;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.system.service.IUserAuthService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("userAuthService")
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements IUserAuthService {

    private UserAuthMapper userAuthMapper;

    private RedisHandler redisHandler;

    @Autowired
    public UserAuthServiceImpl(UserAuthMapper userAuthMapper, RedisHandler redisHandler) {
        this.userAuthMapper = userAuthMapper;
        this.redisHandler = redisHandler;
    }

    /**
     * 通过用户名得到用户（只有部分信息，用于UserDetailsService
     * id userInfoId username password loginType
     * @param username
     * @return
     */
    @Override
    public UserAuthEntity getByUsername(String username) {
        //只获取
         return userAuthMapper.selectOne(
                    new LambdaQueryWrapper<UserAuthEntity>()
                            //select
                            .select(UserAuthEntity::getId, UserAuthEntity::getUserInfoId, UserAuthEntity::getUsername, UserAuthEntity::getPassword, UserAuthEntity::getLoginType)
                            //where
                           .eq(UserAuthEntity::getUsername, username)
         );
    }

    /**
     * 每小时 定时更新已注册用户的地理信息
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void updateStatusOfUserArea() {
        LambdaQueryWrapper<UserAuthEntity> query = new LambdaQueryWrapper<UserAuthEntity>()
                .select(UserAuthEntity::getIpSource);
        // key: 省名，value: 已注册用户数量
        Map<String, Long> userAreaWithCounts = userAuthMapper.selectList(query).stream()
                .map(userAuthEntity -> {
                    //塑性为省的名子
                    String ipSource = userAuthEntity.getIpSource();
                    if (StringUtils.isNotBlank(ipSource) && !CommonConstants.UNKNOWN.equals(ipSource)) {
                        //一般会得到xx省xx市 网络运营商
                        return ipSource.substring(0, 2);
                    } else {
                        return CommonConstants.UNKNOWN;
                    }
                })
                //类似于mysql的groupBy，以同名的source为mapKey，以counting累加结果为mapValue
                .collect(Collectors.groupingBy(
                        source -> source,
                        Collectors.counting()
                ));
        //转换为体积更小的DTO格式，存储到redis中
        List<UserAreaDTO> userAreaDTOList = userAreaWithCounts.entrySet().stream()
                .map(entry -> {
                    return UserAreaDTO.builder()
                            .name(entry.getKey())
                            .value(entry.getValue())
                            .build();
                })
                .collect(Collectors.toList());

        redisHandler.set(RedisConstants.USER_AREA, JSON.toJSONString(userAreaDTOList));
    }
}
