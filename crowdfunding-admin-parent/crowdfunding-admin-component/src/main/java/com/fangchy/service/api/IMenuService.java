package com.fangchy.service.api;

import com.fangchy.entity.Menu;

import java.util.List;

public interface IMenuService {

    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenu(Integer id);
}
