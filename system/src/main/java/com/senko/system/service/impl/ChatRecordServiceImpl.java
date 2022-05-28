package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.ChatRecordMapper;
import com.senko.common.common.entity.ChatRecordEntity;
import com.senko.system.service.IChatRecordService;


@Service("chatRecordService")
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecordEntity> implements IChatRecordService {

}
