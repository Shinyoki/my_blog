package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.core.dto.UniqueViewDTO;
import com.senko.common.core.entity.UniqueViewEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author senko
 * @date 2022-04-24 16:50:47
 */
public interface IUniqueViewService extends IService<UniqueViewEntity> {

    /**
     * 计算得出七天的访问数据
     */
    List<UniqueViewDTO> listOfUniqueViewDTO();
}

