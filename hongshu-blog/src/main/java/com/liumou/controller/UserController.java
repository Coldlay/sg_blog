package com.liumou.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.User;
import com.liumou.service.UploadService;
import com.liumou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author coldplay
 * @create 2023-03-08 10:43
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    UploadService uploadService;

    @RequestMapping("/user/userInfo")
    public ResponseResult getUserInfo(){

        return userService.getUserInfo();
    }
    @PutMapping("/user/userInfo")
    public ResponseResult updateUser(@RequestBody User user){

        return userService.updateUser(user);
    }

    @PostMapping("/upload")
    public ResponseResult upLoad(MultipartFile img){

        return uploadService.upload(img);
    }

    @PostMapping("/user/register")
    public ResponseResult register(@RequestBody String data){

        JSONObject jsonObject = JSONObject.parseObject(data);

        User user = new User();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String nickName = jsonObject.getString("nickName");

        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickName(nickName);

        return userService.register(user);
    }
}
