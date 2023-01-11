package org.cfc.redm.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cfc.redm.framework.security.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询列表
     *
     * @param username 用户名
     * @return List<String>
     */
    List<String> selectListByUsername(@Param("username") String username);

    /**
     * 查询所有权限
     *
     * @return List<String>
     */
    List<String> selectAll();
}