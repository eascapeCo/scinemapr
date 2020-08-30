package com.eascapeco.scinemapr.api.dao.role;

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

    List<Roles> selectRoles();
}
