package com.senko.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senko.common.core.entity.CommentEntity;
import com.senko.system.service.CommentService;

/**
 * 
 *
 * @author senko
 * @date 2022-04-24 16:50:58
 */
@RestController
@RequestMapping("core/comment")
public class CommentController {

}
