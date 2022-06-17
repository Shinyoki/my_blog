package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.OperationLogDTO;
import com.senko.common.core.PageResult;
import com.senko.common.core.entity.OperationLogEntity;
import com.senko.common.core.vo.ConditionVO;

/**
 * 系统日志Service
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IOperationLogService extends IService<OperationLogEntity> {

    /**
     * 查询后台操作记录分页集合
     * @param conditionVO       条件
     * @return                  后台操作记录 分页集合
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);
}

