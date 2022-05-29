package com.senko.system.mapper;

import com.senko.common.common.dto.TalkBackDTO;
import com.senko.common.common.entity.TalkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  说说Mapper
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface TalkMapper extends BaseMapper<TalkEntity> {

    /**
     * 查询后台说说  分页集合
     * @param conditionVO   条件
     * @return              后台说说 分页集合
     */
    List<TalkBackDTO> listTalkBack(@Param("current") Long limitCurrent,@Param("size") Long size,@Param("condition") ConditionVO conditionVO);
}
