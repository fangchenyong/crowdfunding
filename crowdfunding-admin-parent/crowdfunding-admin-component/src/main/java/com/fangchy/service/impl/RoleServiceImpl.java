package com.fangchy.service.impl;

import com.fangchy.entity.Role;
import com.fangchy.mapper.RoleMapper;
import com.fangchy.service.api.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/28 11:25
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.执行查询
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        // 3.封装为pageInfo 对象返回
        return new PageInfo<>(roleList);
    }

    @Override
    public void saveRole(Role role) {

    }

    @Override
    public void updateRole(Role role) {

    }

    @Override
    public void removeRole(List<Integer> roleIdList) {

    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return null;
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return null;
    }
}
