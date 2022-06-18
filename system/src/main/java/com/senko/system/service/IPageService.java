package com.senko.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senko.common.common.dto.PageDTO;
import com.senko.common.common.vo.PageVO;
import com.senko.common.core.entity.PageEntity;

import java.util.List;

/**
 * 页面
 *
 * @author senko
 * @date 2022-04-24 16:50:53
 */
public interface IPageService extends IService<PageEntity> {

    /**
     * 获取页面 分页集合
     */
    List<PageDTO> listPage();

    /**
     * 删除页面
     * @param pageId    页面id
     */
    void deletePageById(Integer pageId);


    /**
     * 保存或更新页面
     * @param pageVO   页面信息VO
     */
    void saveOrUpdatePage(PageVO pageVO);
}

