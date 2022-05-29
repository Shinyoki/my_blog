package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.common.entity.TalkEntity;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;

/**
 * 说说Serivce
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface ITalkService extends IService<TalkEntity> {

    /**
     * 查询后台说说  分页集合
     * @param conditionVO   条件
     * @return              后台说说 分页集合
     */
    PageResult<TalkBackDTO> listTalkBack(ConditionVO conditionVO);
}

