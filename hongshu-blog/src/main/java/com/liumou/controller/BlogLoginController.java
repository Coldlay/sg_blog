package com.liumou.controller;

import com.alibaba.fastjson.JSONObject;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.User;
import com.liumou.enums.AppHttpCodeEnum;
import com.liumou.exception.SystemException;
import com.liumou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author coldplay
 * @create 2023-03-03 9:02
 */
@RestController
public class BlogLoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody String data ){

        JSONObject jsonObject = JSONObject.parseObject(data);
        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
//        @RequestParam("username") String userName,@RequestParam("password") String password

        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        return userService.login(user);
    }

}
