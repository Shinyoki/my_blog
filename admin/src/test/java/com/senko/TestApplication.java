package com.senko;

import com.senko.common.core.dto.ResourceRoleDTO;
import com.senko.common.core.entity.UserAuthEntity;
import com.senko.system.mapper.RoleMapper;
import com.senko.system.service.IRoleService;
import com.senko.system.service.IUserAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
*
* @author senko
* @date 2022/4/26 14:53
*/
@SpringBootTest
public class TestApplication {
    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleService roleService;

    @Test
    void trs() {
        List<String> strings = roleMapper.listRolesByUserInfoId(1);
        strings.stream().forEach(System.out::println);
    }

    @Test
    void testService() {
        System.out.println(userAuthService.getByUsername("admin@qq.com"));
    }

    @Test
    void testRoles() {
        List<ResourceRoleDTO> resourceRoleDTOS = roleMapper.listOfNonAnonymousResourcesRoles();
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleDTOS) {
            System.out.println(resourceRoleDTO);
        }
    }
}
