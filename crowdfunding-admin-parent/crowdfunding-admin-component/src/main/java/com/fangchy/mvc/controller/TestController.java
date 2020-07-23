package com.fangchy.mvc.controller;

import com.fangchy.entity.Admin;
import com.fangchy.entity.ParamData;
import com.fangchy.entity.Student;
import com.fangchy.service.api.IAdminService;
import com.fangchy.util.CrowdUtil;
import com.fangchy.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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


    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student, HttpServletRequest request) {

        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);

        logger.info(student.toString());

        // 将“查询”到的Student对象封装到ResultEntity中返回
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);

        String a = null;

        System.out.println(a.length());

        return resultEntity;
    }

    /*@ResponseBody
    @RequestMapping("/send/compose/object.json")
    public String testReceiveComposeObject(@RequestBody Student student) {
        logger.info(student.toString());
        return "Success";
    }
    */

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array) {

        for (Integer number : array) {
            logger.info("number="+number);
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(ParamData paramData) {

        List<Integer> array = paramData.getArray();

        for (Integer number : array) {
            System.out.println("number="+number);
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {

        for (Integer number : array) {
            System.out.println("number="+number);
        }

        return "success";
    }

    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        System.out.println(10/0);
        return "target";
    }
}
