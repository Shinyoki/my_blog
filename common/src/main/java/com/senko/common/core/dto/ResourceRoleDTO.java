package com.senko.common.core.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 操作菜单Resource 的 权限DTO
 *
 * @author senko
 * @date 2022/4/27 9:45
 */
@Data
@ToString
public class ResourceRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Resource ID
     */
    private Integer id;

    /**
     * 操作路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 所需角色集合
     */
    private List<String> roles;
}
