package com.senko.controller.system;

import com.senko.common.annotation.LogOperation;
import com.senko.common.common.dto.OperationLogDTO;
import com.senko.common.constants.OperationTypeConstants;
import com.senko.common.core.AjaxResult;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.system.service.IOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操作日志Controller
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
@Api(tags = "操作日志模块")
@RestController
public class SysOperationLogController {
    @Autowired
    private IOperationLogService logService;

    /**
     * 查询后台操作记录分页集合
     * @param conditionVO       条件
     * @return                  后台操作记录 分页集合
     */
    @ApiOperation("获取操作日志分页集合")
    @GetMapping("/admin/operation/logs")
    public AjaxResult<PageResult<OperationLogDTO>> listOperationLogs(ConditionVO conditionVO) {
        return AjaxResult.success(logService.listOperationLogs(conditionVO));
    }

    /**
     * 删除操作日志
     * @param logIdList        日志id列表
     */
    @LogOperation(optType = OperationTypeConstants.DELETE)
    @ApiOperation("删除操作日志")
    @DeleteMapping("/admin/operation/logs")
    public AjaxResult<?> deleteOperationLogs(@RequestBody List<Integer> logIdList) {
        logService.removeByIds(logIdList);
        return AjaxResult.success();
    }
}
