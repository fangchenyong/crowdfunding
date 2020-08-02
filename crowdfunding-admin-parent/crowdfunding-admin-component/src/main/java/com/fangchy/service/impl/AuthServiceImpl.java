package com.fangchy.service.impl;

import com.fangchy.entity.Auth;
import com.fangchy.entity.AuthExample;
import com.fangchy.mapper.AuthMapper;
import com.fangchy.service.api.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AuthServiceImpl
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/8/2 21:57
 **/
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelathinship(Map<String, List<Integer>> map) {
        // 1.获取 roleId 的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 2.删除旧关联关系数据
        authMapper.deleteOldRelationship(roleId);
        // 3.获取 authIdList
        List<Integer> authIdList = map.get("authIdArray");
        // 4.判断 authIdList 是否有效
        if (authIdList != null && authIdList.size() > 0) {
            authMapper.insertNewRelationship(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
