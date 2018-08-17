package com.alen.user.handler;

import com.alen.user.common.ExceptionResponse;
import com.alen.user.exception.BusinessException;
import com.alen.user.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Controller全局异常处理
 * Created by jianchen on 2017/8/11.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 错误码说明
     * HTTP 400 - 请求无效
     * HTTP 401 - 未授权：登录失败 （401错误全部跳转至登录页面）
     * HTTP 403 - 没有权限：没有权限  （提示信息）
     * HTTP 404 - （未找到）服务器找不到请求的接口
     * HTTP 406 - 参数错误
     * HTTP 417 - 业务处理异常（自定义错误信息）
     * HTTP 500 - 未定义异常（服务器异常）
     * 其它 未知异常
     */


    /**
     * 自定义业务异常
     * @param e
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @ExceptionHandler(BusinessException.class)
    public ExceptionResponse businessExceptionHandler(BusinessException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uri = httpServletRequest.getRequestURI();
        logger.error("当前访问uri: [{}], 自定义业务异常：[{}]", uri, e);
        httpServletResponse.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.EXPECTATION_FAILED,  e.getMessage());
        return exceptionResponse;
    }


    /**
     * 系统异常
     *
     * @param e
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws SystemException
     */
    @ExceptionHandler(SystemException.class)
    public ExceptionResponse systemExceptionHandler(SystemException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uri = httpServletRequest.getRequestURI();
        logger.error("当前访问uri: [{}], 系统异常（自定义异常）：[{}]", uri, e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,  e.getMessage());
        return exceptionResponse;
    }

    /**
     * IOException
     * @param e
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ExceptionResponse IOExceptionHandler(IOException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uri = httpServletRequest.getRequestURI();
        logger.error("当前访问uri: [{}], io异常：[{}], 异常信息：[{}]", uri, e.getClass(), e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "文件传输异常");
        return exceptionResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse runtimeExceptionHandler(RuntimeException e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uri = httpServletRequest.getRequestURI();
        logger.error("当前访问uri: [{}], 运行时异常：[{}], 异常信息：[{}]", uri, e.getClass(), e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "系统异常");
        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse exceptionHandler(Exception e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uri = httpServletRequest.getRequestURI();
        logger.error("当前访问uri: [{}], 异常类型：[{}], 异常信息：[{}]", uri, e.getClass(), e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR,  "系统异常");
        return exceptionResponse;
    }

}
