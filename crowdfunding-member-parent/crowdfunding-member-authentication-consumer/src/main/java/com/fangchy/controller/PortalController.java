package com.fangchy.controller;

import com.fangchy.api.MySQLRemoteService;
import com.fangchy.constant.CrowdConstant;
import com.fangchy.entity.vo.PortalTypeVO;
import com.fangchy.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: PortalController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/24 23:15
 **/
@Controller
public class PortalController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(Model model) {

        // 1、调用MySQLRemoteService提供的方法查询首页要显示的数据
        ResultEntity<List<PortalTypeVO>> resultEntity =
                mySQLRemoteService.getPortalTypeProjectDataRemote();

        // 2.检查查询结果
        String result = resultEntity.getResult();
        if (ResultEntity.SUCCESS.equals(result)) {

            // 3.获取查询结果数据
            List<PortalTypeVO> list = resultEntity.getData();

            // 4.存入模型
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, list);

        }

        return "portal";
    }
}
