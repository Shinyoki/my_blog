package com.senko.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senko.common.core.PageResult;
import com.senko.common.core.dto.MessageBackDTO;
import com.senko.common.core.vo.ConditionVO;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.page.PageUtils;
import com.senko.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.system.mapper.MessageMapper;
import com.senko.common.core.entity.MessageEntity;
import com.senko.system.service.IMessageService;

import java.util.List;
import java.util.Objects;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询后台留言信息集合
     * @param conditionVO       条件
     * @return                  后台留言 分页集合
     */
    @Override
    public PageResult<MessageBackDTO> listMessageBack(ConditionVO conditionVO) {
        //query
        Page<MessageEntity> page = new Page<>(PageUtils.getCurrent().intValue(), PageUtils.getSize().intValue());
        LambdaQueryWrapper<MessageEntity> query = new LambdaQueryWrapper<MessageEntity>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), MessageEntity::getNickname, conditionVO.getKeywords())
                .eq(Objects.nonNull(conditionVO.getIsReview()), MessageEntity::getIsReview, conditionVO.getIsReview())
                .orderByDesc(MessageEntity::getId);

        Page<MessageEntity> messagePage = messageMapper.selectPage(page, query);
        List<MessageBackDTO> messageBackDTOS = BeanCopyUtils.copyList(messagePage.getRecords(), MessageBackDTO.class);

        return new PageResult<MessageBackDTO>((int) messagePage.getTotal(), messageBackDTOS);
    }
}
