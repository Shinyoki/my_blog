package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.FriendLinkDTO;
import com.senko.common.common.entity.FriendLinkEntity;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;

/**
 * 友链Service
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IFriendLinkService extends IService<FriendLinkEntity> {


    /**
     * 查看友链 分页集合
     * @param condition 条件
     * @return          友链列表
     */
    PageResult<FriendLinkDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 添加或修改友链
     * @param friendLinkDTO 友链信息
     */
    void saveOrUpdateFriendLink(FriendLinkDTO friendLinkDTO);
}

