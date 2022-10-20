package org.cfc.redm.auth.controller;

import org.cfc.redm.auth.dto.UserSaveDTO;
import org.cfc.redm.auth.service.UserService;
import org.cfc.redm.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void save(@RequestBody UserSaveDTO dto) {
        userService.save(dto);
    }
}
