package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.OperationLogDTO;
import com.senko.common.core.PageResult;
import com.senko.common.core.entity.OperationLogEntity;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.OperationLogMapper;
import com.senko.system.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 系统操作日志Service
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogEntity> implements IOperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 查询后台操作记录分页集合
     * @param conditionVO       条件
     * @return                  后台操作记录 分页集合
     */
    @Override
    public PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO) {
        Page<OperationLogEntity> requestPage = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        // 查询日志列表
        Page<OperationLogEntity> resultPage = operationLogMapper.selectPage(requestPage, new LambdaQueryWrapper<OperationLogEntity>()
                //keywords: 操作模块 或 操作描述
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLogEntity::getOptModule, conditionVO.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLogEntity::getOptDesc, conditionVO.getKeywords()));
        List<OperationLogDTO> operationLogDTOList = BeanCopyUtils.copyList(resultPage.getRecords(), OperationLogDTO.class);
        return new PageResult<>((int)resultPage.getTotal(), operationLogDTOList);
    }
}