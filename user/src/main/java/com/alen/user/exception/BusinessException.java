package com.alen.user.exception;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String s)
    {
        super(s);
    }

}
