package org.cfc.redm.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cfc.redm.auth.dto.UserSaveDTO;
import org.cfc.redm.auth.mapper.UserMapper;
import org.cfc.redm.auth.userdetails.CurrentUserBuilder;
import org.cfc.redm.framework.exception.BusinessError;
import org.cfc.redm.framework.exception.BusinessException;
import org.cfc.redm.framework.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 保存
     *
     * @param dto 用户数据
     */
    public void save(UserSaveDTO dto) {
        var queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername());
        // 验证用户名唯一性
        if (userMapper.selectOne(queryWrapper) != null) {
            throw new BusinessException(BusinessError.USERNAME_REPEAT);
        }
        var user = new User();
        user.setUsername(dto.getUsername());
        // 加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encryptedString = encoder.encode(dto.getPassword());
        user.setPassword("{bcrypt}" + encryptedString);
        user.setNickname(dto.getNickname());
        userMapper.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("user:" + username + " does not exist");
        }
        return CurrentUserBuilder.build(user);
    }
}
