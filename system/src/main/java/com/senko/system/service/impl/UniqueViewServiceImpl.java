package com.senko.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.senko.common.core.dto.UniqueViewDTO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UniqueViewMapper;
import com.senko.common.core.entity.UniqueViewEntity;
import com.senko.system.service.IUniqueViewService;

import java.util.List;


@Service("uniqueViewService")
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueViewEntity> implements IUniqueViewService {

    /**
     * 计算得出七天的访问数据
     */
    @Override
    public List<UniqueViewDTO> listOfUniqueViewDTO() {
        return null;
    }


}
