package com.fangchy.service.api;

import com.fangchy.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ClassName: IAdminService
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/21 22:36
 **/
public interface IAdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void remove(Integer adminId);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct2(String username);
}
