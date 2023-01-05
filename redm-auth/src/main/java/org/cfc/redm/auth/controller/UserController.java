package org.cfc.redm.auth.controller;

import org.cfc.redm.auth.dto.UserSaveDTO;
import org.cfc.redm.auth.service.UserService;
import org.cfc.redm.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 保存用户
     *
     * @param dto 用户数据
     * @return Result<Object>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:save')")
    public Result<Object> save(@RequestBody UserSaveDTO dto) {
        userService.save(dto);
        return new Result<>().success();
    }
}
