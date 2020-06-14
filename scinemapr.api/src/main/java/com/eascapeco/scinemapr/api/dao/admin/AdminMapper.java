package com.eascapeco.scinemapr.api.dao.admin;

import java.util.Map;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 관리자 mapper
 * 
 * @author jaehankim
 * @date 2019. 10. 20
 */
@Mapper
public interface AdminMapper {
    public Admin selectAdmin(Admin admin);

    public void insertRefreshToken(AdminToken adminTkn);
}
