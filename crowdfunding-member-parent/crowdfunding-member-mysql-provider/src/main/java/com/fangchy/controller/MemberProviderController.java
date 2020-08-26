package com.fangchy.controller;

import com.fangchy.constant.CrowdConstant;
import com.fangchy.entity.po.MemberPO;
import com.fangchy.service.api.MemberService;
import com.fangchy.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
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

    Logger logger = LoggerFactory.getLogger(MemberProviderController.class);

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

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(
            @RequestBody MemberPO memberPO) {

        logger.info(memberPO.toString());

        try {

            memberService.saveMember(memberPO);

            return ResultEntity.successWithoutData();

        }catch(Exception e) {

            if(e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }

            return ResultEntity.failed(e.getMessage());
        }

    }
}
