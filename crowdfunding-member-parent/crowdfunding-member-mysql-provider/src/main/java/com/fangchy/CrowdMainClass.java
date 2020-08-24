package com.fangchy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: CrowdMainClass
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/23 16:28
 **/
@MapperScan("com.fangchy.mapper")
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class,args);
    }
}
