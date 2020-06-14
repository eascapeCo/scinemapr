package com.eascapeco.scinemapr.api.service.menu;

import com.eascapeco.scinemapr.api.dao.menu.MenuMapper;
import com.eascapeco.scinemapr.api.model.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public Menu createMenu(Menu menu) {
/*
        if () {

        }
*/
        menu.setModNo(menu.getRegNo());
        menu.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        menu.setModDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        System.out.println(menu.toString());
        this.menuMapper.createMenu(menu);

        return menu;
    }

    public List<Menu> getMenuList(Integer admNo) {
        Menu param = new Menu();
        param.setAdmNo(1);

        List<Menu> menus = this.menuMapper.selectMenu(param);
        //System.out.println(list.toString());
/*
        System.out.println("list size " + list.size());
        System.out.println("list size " + list.isEmpty());

        for (Menu m : list) {
            System.out.println("1");
            System.out.println(m.toString());
        }

 */
        return this.getDispMenuList(menus, 1);
    }

    /**
     * 실제 노출할 메뉴 목륵 정보를 변환함
     * @param menuList
     * @param preMnuNo
     * @return
     */
    private List<Menu> getDispMenuList(List<Menu> menuList, int preMnuNo) {
        List<Menu> rv = new ArrayList<>();

        for (Menu menu : menuList) {
            if (menu.getPreMnuNo() == preMnuNo) {
                Menu copyMenu = new Menu();
                BeanUtils.copyProperties(menu, copyMenu);

                copyMenu.setSubMenus(this.getDispMenuList(menuList, copyMenu.getMnuNo()));
                rv.add(copyMenu);
            }
        }

        return rv;
    }
}
