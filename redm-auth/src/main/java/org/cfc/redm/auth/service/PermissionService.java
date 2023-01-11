package org.cfc.redm.auth.service;

import org.cfc.redm.auth.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionService(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public PermissionMapper getBaseMapper() {
        return permissionMapper;
    }

    /**
     * 获取列表
     *
     * @param username 用户名
     * @return List<String>
     */
    public List<String> getList(String username) {
        return permissionMapper.selectListByUsername(username);
    }
}
