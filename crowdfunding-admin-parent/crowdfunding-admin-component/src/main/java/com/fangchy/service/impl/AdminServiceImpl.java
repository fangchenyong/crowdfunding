package com.fangchy.service.impl;

import com.fangchy.constant.CrowdConstant;
import com.fangchy.entity.Admin;
import com.fangchy.entity.AdminExample;
import com.fangchy.exception.LoginFaildException;
import com.fangchy.mapper.AdminMapper;
import com.fangchy.service.api.IAdminService;

import com.fangchy.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: AdminServiceImpl
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/21 22:36
 **/
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
        //throw new RuntimeException();
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询admin对象
        //   创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        //   创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //   在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        //   调用AdminMapper的方法执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 2.查询Admin对象是否为空
        if (admins == null && admins.size() == 0) {
            throw new LoginFaildException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        // 3.如果admin对象为空则抛出异常
        if (admin == null) {
            throw new LoginFaildException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果admin对象不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();
        // 5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswd);
        // 6.对密码进行比较
        if (!Objects.equals(userPswdDB, userPswdForm)) {
            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFaildException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 8.如果一致则返回Admin对象
        return admin;
    }
}
