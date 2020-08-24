package com.fangchy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName: CrowdMainClass
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/24 23:29
 **/
@EnableZuulProxy
@SpringBootApplication
public class CrowdMainClass {

    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class, args);
    }
}
