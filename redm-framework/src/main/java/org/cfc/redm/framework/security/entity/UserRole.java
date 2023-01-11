package org.cfc.redm.framework.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Repository;

@Repository
public class UserRole {

    /**
     * id
     */
    @TableId(value = "id")
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
