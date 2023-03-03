package com.liumou.handler.security;

import com.alibaba.fastjson.JSON;
import com.liumou.domain.ResponseResult;
import com.liumou.enums.AppHttpCodeEnum;
import com.liumou.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author coldplay
 * @create 2023-03-03 15:48
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();

        ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
    }
}
