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

    public List<String> getListByUsername(String username) {
        return permissionMapper.selectListByUsername(username);
    }

}
