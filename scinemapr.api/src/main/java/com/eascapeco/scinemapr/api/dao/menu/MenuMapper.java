package com.eascapeco.scinemapr.api.dao.menu;

import com.eascapeco.scinemapr.api.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * menu mapper
 *
 * @author JaeHan-Kim
 * @date 2020. 05. 05
 */
@Mapper
@Repository
public interface MenuMapper {
    List<Menu> selectMenu(Menu menu);

    void createMenu(Menu menu);

    void updateMenu(Menu menu);
}
