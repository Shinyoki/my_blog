package com.senko.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senko.common.common.dto.PageDTO;
import com.senko.common.common.vo.PageVO;
import com.senko.common.constants.RedisConstants;
import com.senko.common.core.entity.PageEntity;
import com.senko.common.utils.bean.BeanCopyUtils;
import com.senko.common.utils.redis.RedisHandler;
import com.senko.system.mapper.PageMapper;
import com.senko.system.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 页面Service
 */
@Service("pageService")
public class PageServiceImpl extends ServiceImpl<PageMapper, PageEntity> implements IPageService {

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private RedisHandler redisHandler;

    /**
     * 获取页面 分页集合
     */
    @Override
    public List<PageDTO> listPage() {
        //从缓存中获取并判断是否为空，空则从持久层重新获取并赋值
        List<PageDTO> pageDTOList = null;
        Object redisCache = redisHandler.get(RedisConstants.PAGE);
        if (Objects.nonNull(redisCache)) {
            pageDTOList = JSON.parseObject(redisCache.toString(), List.class);
        } else {
            List<PageEntity> pageEntities = pageMapper.selectList(new QueryWrapper<PageEntity>());
            pageDTOList = BeanCopyUtils.copyList(pageEntities, PageDTO.class);

            redisHandler.set(RedisConstants.PAGE, JSON.toJSONString(pageDTOList));
        }
        return pageDTOList;
    }

    /**
     * 删除页面
     * @param pageId    页面id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePageById(Integer pageId) {
        pageMapper.deleteById(pageId);
        //删除缓存
        redisHandler.delete(RedisConstants.PAGE);
    }


    /**
     * 保存或更新页面
     * @param pageVO   页面信息VO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePage(PageVO pageVO) {
        PageEntity pageEntity = BeanCopyUtils.copyObject(pageVO, PageEntity.class);
        this.saveOrUpdate(pageEntity);
        //删除缓存
        redisHandler.delete(RedisConstants.PAGE);
    }
}