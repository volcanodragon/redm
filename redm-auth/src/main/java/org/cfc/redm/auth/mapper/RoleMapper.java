package org.cfc.redm.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cfc.redm.auth.entity.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户名查询超级管理员角色数量
     *
     * @param username 用户名
     * @return 0或1
     */
    int selectSuperAdminByUsername(@Param("username") String username);

}
