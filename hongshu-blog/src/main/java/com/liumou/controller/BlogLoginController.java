package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.User;
import com.liumou.enums.AppHttpCodeEnum;
import com.liumou.exception.SystemException;
import com.liumou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coldplay
 * @create 2023-03-03 9:02
 */
@RestController
public class BlogLoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){

        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        return userService.login(user);
    }

}
