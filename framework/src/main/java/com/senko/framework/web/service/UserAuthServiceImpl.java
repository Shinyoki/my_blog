package com.senko.framework.web.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.UserAreaDTO;
import com.senko.common.constants.CommonConstants;
import com.senko.common.constants.RabbitMQConstants;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.EmailDTO;
import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.dto.UserLoginInfoDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.common.core.entity.UserInfoEntity;
import com.senko.common.core.entity.UserRoleEntity;
import com.senko.common.core.vo.*;
import com.senko.common.enums.LoginTypeEnum;
import com.senko.common.enums.UserTypeEnum;
import com.senko.common.exceptions.service.ServiceException;
import com.senko.common.utils.http.ServletUtils;
import com.senko.common.utils.ip.IpUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.common.utils.spring.SecurityUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.framework.strategy.context.SocialLoginStrategyContext;
import com.senko.system.mapper.UserAuthMapper;
import com.senko.system.mapper.UserInfoMapper;
import com.senko.system.mapper.UserRoleMapper;
import com.senko.system.service.IUserAuthService;
import com.senko.system.service.IWebsiteConfigService;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 后台用户模块
 */
@Service("userAuthService")
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuthEntity> implements IUserAuthService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SocialLoginStrategyContext loginStrategyContext;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IWebsiteConfigService websiteConfigService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;



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

    @Override
    public void sendEmailValidCode(String username) {
        // 校验邮箱地址是否正确
        if (StringUtils.notEmailAddress(username)) {
            throw new ServiceException("邮箱地址格式错误，请输入正确的邮箱地址");
        }

        // 生成验证码
        String code = StringUtils.getRandomCode(6);

        // 将code缓存到redis中   key: code + username   value: code   ttl: 15分钟
        redisHandler.set(RedisConstants.CODE_KEY + username, code, RedisConstants.CODE_EXPIRE_TIME);

        // 发送邮件
        EmailDTO email = EmailDTO.builder()
                // 需要发送的邮箱地址
                .email(username)
                // 邮件主题
                .subject("SenkoTech")
                // 验证码
                .content("您的验证码是：<strong>" + code + "</strong>，请在15分钟内使用。")
                .build();

        logger.info("{}的邮件验证码：{}", username, code);

        // rabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.EMAIL_EXCHANGE,
                "*",
                new Message(JSON.toJSONBytes(email), new MessageProperties())
                );

    }

    /**
     * 注册用户
     * @param userVO    用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doRegister(UserVO userVO) {
        // 检验邮箱地址是否存在
        if (checkEmailExisted(userVO.getUsername())) {
            throw new ServiceException("邮箱地址已存在");
        }

        // 校验验证码是否一致
        String cacheCode = (String) redisHandler.get(RedisConstants.CODE_KEY + userVO.getUsername());
        if (Objects.isNull(cacheCode))
            throw new ServiceException("验证码已过期，请重新获取");
        if (!cacheCode.equals(userVO.getCode()))
            throw new ServiceException("验证码错误");

        // 新增用户信息
        UserInfoEntity userInfo = UserInfoEntity.builder()
                // 邮箱
                .email(userVO.getUsername())
                // 昵称使用默认随机
                .nickname(CommonConstants.DEFAULT_NICKNAME + IdWorker.getId())
                // 头像使用默认
                .avatar(websiteConfigService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);

        // 绑定role
        UserRoleEntity userRole = UserRoleEntity.builder()
                // 默认用户role
                .roleId(UserTypeEnum.USER.getRoleId())
                // 用户id
                .userId(userInfo.getId())
                .build();
        userRoleMapper.insert(userRole);

        // 绑定Auth
        String ipAddress = IpUtils.getIpAddressFromRequest(ServletUtils.getRequest());
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAuthEntity userAuth = UserAuthEntity.builder()
                // 用户id
                .userInfoId(userInfo.getId())
                // ip地址
                .ipAddress(ipAddress)
                // ip来源
                .ipSource(ipSource)
                // 登录类型
                .loginType(LoginTypeEnum.EMAIL.getType())
                // 登录时间
                .lastLoginTime(LocalDateTime.now())
                // 密码
                .password(passwordEncoder.encode(userVO.getPassword()))
                // 用户名
                .username(userVO.getUsername())
                .build();
        userAuthMapper.insert(userAuth);
    }

    /**
     * 检验邮箱地址是否已经存在
     * @param username      邮箱地址
     * @return              true：存在，false：不存在
     */
    private boolean checkEmailExisted(String username) {
        return null != userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuthEntity>()
                .eq(UserAuthEntity::getUsername, username));
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
