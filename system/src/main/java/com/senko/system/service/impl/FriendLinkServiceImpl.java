package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.FriendLinkMapper;
import com.senko.common.core.entity.FriendLinkEntity;
import com.senko.system.service.IFriendLinkService;


@Service("friendLinkService")
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLinkEntity> implements IFriendLinkService {

}