package com.liumou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.LoginUser;
import com.liumou.domain.entity.User;
import com.liumou.domain.vo.BlogUserLoginVo;
import com.liumou.domain.vo.UserInfoVo;
import com.liumou.enums.AppHttpCodeEnum;
import com.liumou.exception.SystemException;
import com.liumou.mapper.UserMapper;
import com.liumou.service.UserService;
import com.liumou.utils.BeanCopyUtils;
import com.liumou.utils.JwtUtil;
import com.liumou.utils.RedisCache;
import com.liumou.utils.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author coldplay
 * @create 2023-03-03 9:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //获取userid生成token
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+id,loginUser);
        //将token和userInfo封装 返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {

        //从SecurityContextHolder中获取Authentication对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //从Authentication对象中获取LoginUser对象
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();

        //通过LoginUser对象获取用户id
        Long id = loginUser.getUser().getId();

        //在redis中删除用户的记录
        redisCache.deleteObject("bloglogin:" + id);



        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserInfo() {
        //通过工具类获取用户id
        Long userId = SecurityUtils.getUserId();

        User byId = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(byId, UserInfoVo.class);

        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUser(User user) {

        int i = userMapper.updateById(user);

        if(i == 1){
            return ResponseResult.okResult();
        }else{
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
    }
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseResult register(User user) {

        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }

        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }


        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        int insert = userMapper.insert(user);

        if(insert == 1){
            return ResponseResult.okResult(user);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.INPUT_ERROR);
    }

    private Boolean userNameExist(String userName){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        return count(userLambdaQueryWrapper) > 0;
    }

}
