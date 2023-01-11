package org.cfc.redm.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cfc.redm.framework.security.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询是否是超级管理员
     *
     * @param username 用户名
     * @return Boolean
     */
    Boolean selectIsSuperAdminByUsername(@Param("username") String username);
}
