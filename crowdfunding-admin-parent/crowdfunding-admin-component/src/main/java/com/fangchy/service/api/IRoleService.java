package com.fangchy.service.api;

import com.fangchy.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {

    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
