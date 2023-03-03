package com.liumou.handler.security;

import com.alibaba.fastjson.JSON;
import com.liumou.domain.ResponseResult;
import com.liumou.enums.AppHttpCodeEnum;
import com.liumou.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author coldplay
 * @create 2023-03-03 15:43
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , AuthenticationException e) throws IOException, ServletException {


        ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);

        WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
    }
}
