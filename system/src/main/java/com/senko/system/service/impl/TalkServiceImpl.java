package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.TalkMapper;
import com.senko.common.common.entity.TalkEntity;
import com.senko.system.service.ITalkService;


@Service("talkService")
public class TalkServiceImpl extends ServiceImpl<TalkMapper, TalkEntity> implements ITalkService {

}
