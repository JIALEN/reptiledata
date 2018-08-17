package com.alen.user.handler;

import com.alen.user.utill.CustomerAccessObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局日志处理
 *
 * @author alen
 * @create 2018-08-09 16:52
 **/
@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //定义线程本地变量，这里的NamedThreadLocal只是1个带name属性的ThreadLocal：
    private static final ThreadLocal<Map<String, Object>> threadLocal =
            new NamedThreadLocal<>("ThreadLocal Info");

    /**
     * 定义一个切入点.
     */
    @Pointcut("execution(public * com.alen.user.controller..*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        long beginTime = System.currentTimeMillis();//1、开始时间
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime", beginTime);
        map.put("url", request.getRequestURL().toString());
        map.put("httpMethod", request.getMethod());
        map.put("ip", CustomerAccessObjectUtil.getIpAddress(request));
        map.put("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        map.put("params", Arrays.toString(joinPoint.getArgs()));
        threadLocal.set(map);        //线程绑定变量（该数据只有当前请求的线程可见）
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        Map<String, Object> map = threadLocal.get();
        long beginTime = (long) map.get("beginTime");//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();  //2、结束时间
        logger.info("请求地址 ：[{}], HTTP_METHOD ：[{}], IP ：[{}], CLASS_METHOD ：[{}], 参数 ：[{}], 返回值 ：[{}], 耗时 ：[{}]",
                map.get("url"),
                map.get("httpMethod"),
                map.get("ip"),
                map.get("classMethod"),
                map.get("params"),
                ret,
                (endTime - beginTime));
    }

    @AfterThrowing(throwing = "ex", pointcut = "logPointCut()")
    public void doAfterThrowing(Throwable ex) throws Throwable {
        Map<String, Object> map = threadLocal.get();
        String exStr = "";
        // 发生异常地点
        StackTraceElement[] st = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : st) {
            int lineNum = stackTraceElement.getLineNumber();
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            exStr = "类:" + className + "调用"
                    + methodName + "时在第" + lineNum
                    + "行代码处发生异常!异常类型:" + ex.getClass().getName();
            break;
        }
        long beginTime = (long) map.get("beginTime");//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();  //2、结束时间

        logger.info("请求地址 ：[{}], HTTP_METHOD ：[{}], IP ：[{}], CLASS_METHOD ：[{}], 参数 ：[{}], 异常 ：[{}], 耗时 ：[{}]",
                map.get("url"),
                map.get("httpMethod"),
                map.get("ip"),
                map.get("classMethod"),
                map.get("params"),
                exStr,
                (endTime - beginTime));
    }

}
