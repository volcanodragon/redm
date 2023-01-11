package org.cfc.redm.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cfc.redm.framework.security.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

}
