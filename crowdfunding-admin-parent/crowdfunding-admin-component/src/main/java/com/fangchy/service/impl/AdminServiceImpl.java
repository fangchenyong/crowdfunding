package com.fangchy.service.impl;

import com.fangchy.entity.Admin;
import com.fangchy.entity.AdminExample;
import com.fangchy.mapper.AdminMapper;
import com.fangchy.service.api.IAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
