package com.eascapeco.scinemapr.api.dao.role;

import com.eascapeco.scinemapr.api.model.MenuRoles;
import com.eascapeco.scinemapr.api.model.Roles;
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
public interface RolesMapper {

    List<Roles> selectRoles(Roles roles);

    Roles selectOneRoles(Integer id);

    void insertRoles(Roles roles);

    List<MenuRoles> selectRoleMenus(String rolNm);
    
    int selectRolesCount(Roles roles);

    List<Roles> selectRoleList();
}
