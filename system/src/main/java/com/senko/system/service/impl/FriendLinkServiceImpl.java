package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.FriendLinkDTO;
import com.senko.common.common.entity.FriendLinkEntity;
import com.senko.common.core.PageResult;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import com.senko.system.mapper.FriendLinkMapper;
import com.senko.system.service.IFriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 友链Service
 */
@Service("friendLinkService")
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLinkEntity> implements IFriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    /**
     * 查看友链 分页集合
     * @param condition 条件
     * @return          友链列表
     */
    @Override
    public PageResult<FriendLinkDTO> listFriendLinkDTO(ConditionVO condition) {
        Page<FriendLinkEntity> request = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        Page<FriendLinkEntity> friendLinkEntityPage = friendLinkMapper.selectPage(request, new LambdaQueryWrapper<FriendLinkEntity>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), FriendLinkEntity::getLinkName, condition.getKeywords()));
        List<FriendLinkDTO> resultDTOList = BeanCopyUtils.copyList(friendLinkEntityPage.getRecords(), FriendLinkDTO.class);
        return new PageResult<>((int)friendLinkEntityPage.getTotal(), resultDTOList);
    }

    /**
     * 添加或修改友链
     * @param friendLinkDTO 友链信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateFriendLink(FriendLinkDTO friendLinkDTO) {
        FriendLinkEntity friendLinkEntity = BeanCopyUtils.copyObject(friendLinkDTO, FriendLinkEntity.class);
        this.saveOrUpdate(friendLinkEntity);
    }
}
