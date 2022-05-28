package com.senko.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.senko.common.common.dto.UniqueViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.UniqueViewMapper;
import com.senko.common.common.entity.UniqueViewEntity;
import com.senko.system.service.IUniqueViewService;

import java.util.Date;
import java.util.List;


@Service("uniqueViewService")
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueViewEntity> implements IUniqueViewService {

    private UniqueViewMapper uniqueViewMapper;

    @Autowired
    public UniqueViewServiceImpl(UniqueViewMapper uniqueViewMapper) {
        this.uniqueViewMapper = uniqueViewMapper;
    }

    /**
     * 计算得出七天的访问数据
     */
    @Override
    public List<UniqueViewDTO> listOfUniqueViewDTO() {
        //yyyy-MM-dd 00:00:00
        DateTime sevenDaysAgo = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        //yyyy-MM-dd 23:59:59
        DateTime endOfToday = DateUtil.endOfDay(new Date());
        //统计这段时间里的浏览量
        return uniqueViewMapper.listOfUniqueViewDTO(sevenDaysAgo, endOfToday);
    }


}
