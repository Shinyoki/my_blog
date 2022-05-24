package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.MessageBackDTO;
import com.senko.common.core.entity.MessageEntity;
import com.senko.common.core.vo.ConditionVO;

import java.util.Map;

/**
 * 留言Service
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IMessageService extends IService<MessageEntity> {

    /**
     * 查询后台留言信息集合
     * @param conditionVO       条件
     * @return                  后台留言 分页集合
     */
    PageResult<MessageBackDTO> listMessageBack(ConditionVO conditionVO);
}

