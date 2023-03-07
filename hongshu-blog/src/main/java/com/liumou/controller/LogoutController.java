package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coldplay
 * @create 2023-03-05 18:53
 */
@RestController
public class LogoutController {

    @Autowired
    UserService userService;

    @RequestMapping("/logout")
    public ResponseResult logOut(){

        return userService.logout();
    }
}
