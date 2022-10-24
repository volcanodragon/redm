package org.cfc.redm.auth.userdetails;

import org.cfc.redm.auth.entity.User;
import org.cfc.redm.auth.service.PermissionService;
import org.cfc.redm.auth.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;

@Component
public class CompleteUserInfoBuilder {

    private static PermissionService permissionService;
    private static RoleService roleService;

    private final PermissionService permissionServiceBean;
    private final RoleService roleServiceBean;

    @Autowired
    public CompleteUserInfoBuilder(PermissionService permissionServiceBean,
                                   RoleService roleServiceBean) {
        this.permissionServiceBean = permissionServiceBean;
        this.roleServiceBean = roleServiceBean;
    }

    @PostConstruct
    public void init() {
        CompleteUserInfoBuilder.permissionService = permissionServiceBean;
        CompleteUserInfoBuilder.roleService = roleServiceBean;
    }

    public static UserDetails build(User user) {
        List<String> permissions;
        // 如果是超级管理员则获取所有权限
        if (roleService.isSuperAdmin(user.getUsername())) {
            permissions = permissionService.getBaseMapper().selectAll();
        } else {
            permissions = permissionService.getListByUsername(user.getUsername());
        }
        var permissionArr = new String[permissions.size()];
        permissions.toArray(permissionArr);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(permissionArr);
        var completeUserInfo = new CompleteUserInfo(new HashSet<>(grantedAuthorities), true, true, true, true);
        BeanUtils.copyProperties(user, completeUserInfo);
        return completeUserInfo;
    }

}
