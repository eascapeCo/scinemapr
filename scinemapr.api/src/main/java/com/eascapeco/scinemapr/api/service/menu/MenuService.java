package com.eascapeco.scinemapr.api.service.menu;

import com.eascapeco.scinemapr.api.dao.menu.MenuMapper;
import com.eascapeco.scinemapr.api.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 메뉴 service implements
 *
 * @author JaeHan-Kim
 * @date 2020. 05. 03
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    /**
     * 메뉴를 생성
     * @return
     */
    public Menu createMenu() {
        return null;
    }

    public List<Menu> getMenuList(Menu menu) {
        List<Menu> list = this.menuMapper.selectMenu(menu);
        //System.out.println(list.toString());
/*
        System.out.println("list size " + list.size());
        System.out.println("list size " + list.isEmpty());

        for (Menu m : list) {
            System.out.println("1");
            System.out.println(m.toString());
        }

 */
        return list;
    }
}
