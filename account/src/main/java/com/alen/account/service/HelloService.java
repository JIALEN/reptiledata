package com.alen.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author alen
 * @create 2018-08-11 9:35
 **/
@Service
public class HelloService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;
    @Value("${consumerURL}")
    private String consumerUrl;

    public String getAge(Integer age) {
        //打印一下到底调用的是哪个
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user");
        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() +
                ":" + serviceInstance.getPort());
        System.out.print(consumerUrl);

        return restTemplate.getForObject(consumerUrl + age, String.class);
    }
}
