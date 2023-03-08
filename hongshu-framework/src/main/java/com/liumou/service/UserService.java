package com.liumou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author coldplay
 * @create 2023-03-03 9:04
 */
public interface UserService extends IService<User> {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult getUserInfo();

    ResponseResult updateUser(User user);

    ResponseResult register(User user);
}
