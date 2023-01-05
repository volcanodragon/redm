package org.cfc.redm.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Repository;

@Repository
public class RolePermission {

    /**
     * id
     */
    @TableId(value = "id")
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
