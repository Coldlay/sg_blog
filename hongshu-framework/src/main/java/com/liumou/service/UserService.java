package com.liumou.service;

import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.User;

/**
 * @author coldplay
 * @create 2023-03-03 9:04
 */
public interface UserService {
    ResponseResult login(User user);
}
