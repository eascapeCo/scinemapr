package com.eascapeco.scinemapr.api.service.menu;

import com.eascapeco.scinemapr.api.dao.menu.MenuMapper;
import com.eascapeco.scinemapr.api.exception.BadRequestException;
import com.eascapeco.scinemapr.api.model.Menu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 메뉴를 생성
     * @return
     */
    public Menu createMenu(Menu menu) {

        Menu parentMenu = this.menuMapper.selectMenu(menu.getMnuNo());

        if (parentMenu == null) {
            throw new BadRequestException("!!!!");
        }

        menu.setModNo(menu.getRegNo());
        menu.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        menu.setModDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        log.info("{}", menu);
        //this.menuMapper.createMenu(menu);

        return menu;
    }

    /**
     * 메뉴 번호롤 메뉴 정보 조회
     * @param mnuNo
     * @return
     */
    public Menu getMenu(Integer mnuNo) {
        return this.menuMapper.selectMenu(mnuNo);
    }

    /**
     * 관리자가 접근 가능한 메뉴 목록을 조회
     * @param admNo
     * @return
     */
    public List<Menu> getMenuList(Integer admNo) {
        Menu param = new Menu();
        param.setUseYn(true);

        if (admNo != null) {
            param.setAdmNo(admNo);
            param.setDpYn(true);
        }

        List<Menu> menus = this.menuMapper.selectMenus(param);

        return this.getDispMenuList(menus, 1);
    }

    public Menu updateMenu(Integer mnuNo, Menu menu) {

        Menu updateMenu = new Menu();

        updateMenu.setMnuNo(mnuNo);
        updateMenu.setMnuName(menu.getMnuName());
        updateMenu.setUrlAdr(menu.getUrlAdr());
        updateMenu.setDpYn(menu.isDpYn());
        updateMenu.setDpSequence(menu.getDpSequence());
        updateMenu.setModNo(1);
        updateMenu.setModDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        this.menuMapper.updateMenu(updateMenu);

        return updateMenu;
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
