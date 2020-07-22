package com.fangchy.mvc.controller;

import com.fangchy.entity.Admin;
import com.fangchy.service.api.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/22 17:46
 **/
@Controller
public class TestController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }
}
