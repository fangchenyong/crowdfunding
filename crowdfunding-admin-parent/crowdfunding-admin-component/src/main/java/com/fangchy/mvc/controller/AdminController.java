package com.fangchy.mvc.controller;

import com.fangchy.constant.CrowdConstant;
import com.fangchy.entity.Admin;
import com.fangchy.service.api.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: AdminController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/23 23:04
 **/
@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session) {
        //调用Service方法执行登录检查
        //如果能成功返回admin对象则登录成功，如果账号密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        //将登录成功返回的admin对象存入Session
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
}
