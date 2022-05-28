package com.senko.system.mapper;

import com.senko.common.core.dto.UserBackDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senko.common.core.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户Mapper
 *
 * @author senko
 * @date 2022-04-24 16:39:55
 */
@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuthEntity> {

    /**
     * 查询符合条件的后台用户数量
     * @param conditionVO   条件
     * @return              后台用户数量
     */
    Integer selectCountByConditionVO(@Param("condition") ConditionVO conditionVO);

    /**
     * 查询符合条件的后台用户 分页集合
     * @param condition   条件
     * @return              后台用户DTO 分页集合
     */
    List<UserBackDTO> listUserBack(@Param("current") Long current,@Param("size") Long size,@Param("condition") ConditionVO condition);
}
