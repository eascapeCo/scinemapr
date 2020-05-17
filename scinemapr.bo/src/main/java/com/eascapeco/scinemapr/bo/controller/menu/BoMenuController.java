package com.eascapeco.scinemapr.bo.controller.menu;

import com.eascapeco.scinemapr.api.model.Menu;
import com.eascapeco.scinemapr.api.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> retriveAllmenu() {
        System.out.println("get menu");

        List<Menu> list = this.menuService.getMenuList(null);
        for (Menu m : list) {
            System.out.println(m.getMnuName());
        }

        return this.menuService.getMenuList(null);
    }

    @PostMapping("/menus")
    public Menu menu2() {
        return new Menu();
    }
}
