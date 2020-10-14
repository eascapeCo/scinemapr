package com.eascapeco.scinemapr.api.dao.admin;

import java.util.List;
import java.util.Map;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.RefreshToken;
import com.eascapeco.scinemapr.api.model.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 관리자 mapper
 * 
 * @author jaehankim
 * @Date 2019. 10. 20
 */
@Mapper
@Repository
public interface AdminMapper {
    public Admin selectAdmin(String id);

    public void insertRefreshToken(RefreshToken tkn);

    public List<Admin> selectAdminList();
}
