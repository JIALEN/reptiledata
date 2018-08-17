package com.alen.hystrixservice.service.impl;

import com.alen.hystrixservice.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author alen
 * @create 2018-08-17 13:38
 **/
@Service
public class HelloServiceImpl implements HelloService {
    private static Logger logger=LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public Integer hello(Integer age) {
        logger.info("调用hello失败"+"---请求参数为："+age);
        return age;
    }
}
