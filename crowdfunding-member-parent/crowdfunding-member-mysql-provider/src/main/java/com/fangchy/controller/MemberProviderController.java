package com.fangchy.controller;

import com.fangchy.entity.po.MemberPO;
import com.fangchy.service.api.MemberService;
import com.fangchy.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MemberProviderController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/24 22:25
 **/
@RestController
public class MemberProviderController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct) {

        try {

            // 1.调用本地Service完成查询
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

            // 2.如果没有抛异常，那么就返回成功的结果
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();

            // 3.如果捕获到异常则返回失败的结果
            return ResultEntity.failed(e.getMessage());
        }

    }

}
