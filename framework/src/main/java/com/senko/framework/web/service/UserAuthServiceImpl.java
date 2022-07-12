package com.senko.framework.web.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.UserAreaDTO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.core.vo.GithubVO;
import com.senko.common.core.vo.QQLoginVO;
import com.senko.common.core.vo.UserPasswordVO;
import com.senko.common.enums.LoginTypeEnum;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.framework.strategy.context.SocialLoginStrategyContext;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.system.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 后台用户模块
 */
@Service("userAuthService")
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements IUserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SocialLoginStrategyContext loginStrategyContext;


    /**
     * 通过用户名得到用户（只有部分信息，用于UserDetailsService
     * id userInfoId username password loginType
     *
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
     * 查询后台用户 分页集合
     *
     * @param conditionVO 条件（用户名、登陆类型）
     * @return 后台用户 分页集合
     */
    @Override
    public PageResult<UserBackDTO> listUserBack(ConditionVO conditionVO) {
        Integer count = userAuthMapper.selectCountByConditionVO(conditionVO);
        if (count == 0) {
            return new PageResult<>();
        }

        List<UserBackDTO> userBackDTOList = userAuthMapper.listUserBack(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        return new PageResult<UserBackDTO>(count, userBackDTOList);
    }

    /**
     * 更新用户密码
     *
     * @param userPasswordVO 用户id、用户名、密码
     */
    @Override
    public void updateUserPassword(UserPasswordVO userPasswordVO) {
        UserAuthEntity userAuthEntity = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuthEntity>()
                .select(UserAuthEntity::getId, UserAuthEntity::getPassword)
                .eq(UserAuthEntity::getId, SecurityUtils.getLoginUser().getId()));
        if (Objects.nonNull(userAuthEntity) && userAuthEntity.getPassword().equals(passwordEncoder.encode(userPasswordVO.getOldPassword()))) {
            //更新密码
            userAuthEntity.setPassword(passwordEncoder.encode(userPasswordVO.getNewPassword()));
            userAuthMapper.updateById(userAuthEntity);
        } else {
            throw new RuntimeException("原密码错误");
        }
    }

    /**
     * qq登录
     * @param loginVO        登录信息
     * @return               用户信息
     */
    @Override
    public UserLoginInfoDTO qqLogin(QQLoginVO loginVO) {
        return loginStrategyContext
                .executeLogin(JSON.toJSONString(loginVO), LoginTypeEnum.QQ);
    }

    /**
     * Github登录
     * @param githubVO        登录信息
     * @return               登录后的用户
     */
    @Override
    public UserLoginInfoDTO githubLogin(GithubVO githubVO) {
        return loginStrategyContext
                .executeLogin(JSON.toJSONString(githubVO), LoginTypeEnum.GITHUB);
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
