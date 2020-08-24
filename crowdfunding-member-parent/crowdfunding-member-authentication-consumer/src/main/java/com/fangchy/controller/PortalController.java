package com.fangchy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PortalController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/24 23:15
 **/
@Controller
public class PortalController {
    @RequestMapping("/")
    public String showPortalPage() {

        // 这里实际开发中需要加载数据……

        return "portal";
    }
}
