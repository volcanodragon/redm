package org.cfc.redm.auth.service;

import org.cfc.redm.auth.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 是否是超级管理员
     *
     * @param username 用户名
     * @return Boolean
     */
    public Boolean isSuperAdmin(String username) {
        return roleMapper.selectIsSuperAdminByUsername(username);
    }
}
