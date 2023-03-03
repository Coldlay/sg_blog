package com.liumou.exception;

import com.liumou.enums.AppHttpCodeEnum;

/**
 * @author coldplay
 * @create 2023-03-03 16:06
 */
public class SystemException extends RuntimeException{

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum){
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
