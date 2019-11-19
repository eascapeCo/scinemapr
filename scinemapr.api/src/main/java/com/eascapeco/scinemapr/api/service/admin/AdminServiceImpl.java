package com.eascapeco.scinemapr.api.service.admin;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eascapeco.scinemapr.api.dao.admin.AdminMapper;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.Result;

/**
 * 관리자 service implements
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminDao;

    @Override
    public Admin getAdmin(Admin admin) {
        Admin adminInfo = adminDao.selectAdmin(admin);

        return adminInfo;
    }

    @Override
    public void insertRefreshToken(AdminToken adminTkn) {
        adminDao.insertRefreshToken(adminTkn);

    }
}