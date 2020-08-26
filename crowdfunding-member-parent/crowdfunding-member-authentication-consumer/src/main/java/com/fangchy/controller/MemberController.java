package com.fangchy.controller;

import com.fangchy.api.MySQLRemoteService;
import com.fangchy.api.RedisRemoteService;
import com.fangchy.constant.CrowdConstant;
import com.fangchy.entity.po.MemberPO;
import com.fangchy.entity.vo.MemberLoginVO;
import com.fangchy.entity.vo.MemberVO;
import com.fangchy.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MemberController
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/26 20:57
 **/
@Controller
public class MemberController {

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {

        // 生成验证码
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            code.append(random);
        }

        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);

        if (resultEntity.getData() != null) {
            //如果已经有值了 直接取出来
            return resultEntity;
        } else {
            //否则插入值
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code.toString(), 15, TimeUnit.MINUTES);
            //不管成功与否，都返回saveCodeResultEntity
            saveCodeResultEntity.setData(code.toString());
            return saveCodeResultEntity;
            /*if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
                return ResultEntity.successWithoutData();
            } else {
                return saveCodeResultEntity;
            }*/
        }

    }


    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO, ModelMap modelMap) {
        // 1.获取用户输入的手机号
        String phoneNum = memberVO.getPhoneNum();

        // 2.拼Redis中存储验证码的Key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        // 3.从Redis读取Key对应的value
        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);

        // 4.检查查询操作是否有效
        String result = resultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());

            return "member-reg";
        }

        String redisCode = resultEntity.getData();

        if (redisCode == null) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);

            return "member-reg";
        }

        // 5.如果从Redis能够查询到value则比较表单验证码和Redis验证码
        String formCode = memberVO.getCode();

        if (!Objects.equals(formCode, redisCode)) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);

            return "member-reg";
        }

        // 6.如果验证码一致，则从Redis删除
        redisRemoteService.removeRedisKeyRemote(key);

        // 7.执行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userpswdBeforeEncode = memberVO.getUserpswd();

        String userpswdAfterEncode = passwordEncoder.encode(userpswdBeforeEncode);

        memberVO.setUserpswd(userpswdAfterEncode);

        // 8.执行保存
        // ①创建空的MemberPO对象
        MemberPO memberPO = new MemberPO();

        // ②复制属性
        BeanUtils.copyProperties(memberVO, memberPO);

        // ③调用远程方法
        ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMember(memberPO);

        if (ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveMemberResultEntity.getMessage());

            return "member-reg";
        }

        // 使用重定向避免刷新浏览器导致重新执行注册流程
        return "redirect:/auth/member/to/login/page";
    }

    @RequestMapping("/auth/member/do/login")
    public String login(
            @RequestParam("loginacct") String loginacct,
            @RequestParam("userpswd") String userpswd,
            ModelMap modelMap,
            HttpSession session) {

        // 1.调用远程接口根据登录账号查询MemberPO对象
        ResultEntity<MemberPO> resultEntity =
                mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);

        if(ResultEntity.FAILED.equals(resultEntity.getResult())) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());

            return "member-login";

        }

        MemberPO memberPO = resultEntity.getData();

        if(memberPO == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";
        }

        // 2.比较密码
        String userpswdDataBase = memberPO.getUserpswd();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean matcheResult = passwordEncoder.matches(userpswd, userpswdDataBase);

        if(!matcheResult) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";
        }

        // 3.创建MemberLoginVO对象存入Session域
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER, memberLoginVO);

        return "redirect:/auth/member/to/center/page";
    }

    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}
