package com.fangchy.service.api;

import com.fangchy.entity.Admin;

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
}
