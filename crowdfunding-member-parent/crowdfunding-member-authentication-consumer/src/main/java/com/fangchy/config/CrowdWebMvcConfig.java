package com.fangchy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: CrowdWebMvcConfig
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/26 15:08
 **/
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问的地址
        String urlPath = "/auth/member/to/reg/page";

        // 目标视图的名称，将来拼接“prefix: classpath:/templates/”、“suffix: .html”前后缀
        String viewName = "member-reg";

        // 添加view-controller
        registry.addViewController(urlPath).setViewName(viewName);;
        registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
    }
}
