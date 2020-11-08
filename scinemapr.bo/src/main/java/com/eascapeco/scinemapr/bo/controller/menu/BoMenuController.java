package com.eascapeco.scinemapr.bo.controller.menu;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.Menu;
import com.eascapeco.scinemapr.api.model.Result;
import com.eascapeco.scinemapr.api.service.menu.MenuService;
import com.eascapeco.scinemapr.bo.controller.auth.BoAuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BoMenuController {

    private final Logger log = LoggerFactory.getLogger(BoMenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> retriveAllmenu(@AuthenticationPrincipal Admin loginUser) {
        log.info("get menu");
        log.info("login user {}", loginUser.getAdmNo());
        List<Menu> list = this.menuService.getMenuList(null);

        return list;
    }

    @GetMapping("/menus/{id}")
    public Menu getMenu(@PathVariable Integer id) {


        return this.menuService.getMenu(id);
    }

    @PostMapping("/menus")
    public ResponseEntity<Menu> saveMenu(/*@AuthenticationPrincipal Admin loginUser, */@RequestBody @Valid Menu menu) {
        /*
        log.info("user name -> {}", loginUser.getUsername());
        log.info("user admNo -> {}", loginUser.getAdmNo());
         */
        log.info("{}", menu);
        log.info("!@# {}", menu.getCreateType());

        Menu savedMenu = this.menuService.createMenu(menu);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{mnuNo}")
                .buildAndExpand(savedMenu.getMnuNo())
                .toUri();
        log.info("menu {} ", menu.toString());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer id, @RequestBody Menu menu) {
        log.info("{}", menu);
        log.info("{}", id);
        Menu updateMenu = this.menuService.updateMenu(id, menu);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{mnuNo}")
                .buildAndExpand(updateMenu.getMnuNo())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
