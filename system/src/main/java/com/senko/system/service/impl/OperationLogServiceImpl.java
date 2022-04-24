package com.senko.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.OperationLogMapper;
import com.senko.common.core.entity.OperationLogEntity;
import com.senko.system.service.IOperationLogService;


@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogEntity> implements IOperationLogService {

}