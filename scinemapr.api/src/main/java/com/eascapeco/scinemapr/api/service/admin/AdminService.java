package com.eascapeco.scinemapr.api.service.admin;

import java.util.Map;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.Result;

/**
 * 관리자 service
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
public interface AdminService {

    Admin getAdmin (String id);

    void insertRefreshToken(AdminToken adminTkn);
}