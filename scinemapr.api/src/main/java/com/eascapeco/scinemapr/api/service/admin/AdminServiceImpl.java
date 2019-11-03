package com.eascapeco.scinemapr.api.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eascapeco.scinemapr.api.dao.admin.AdminMapper;
import com.eascapeco.scinemapr.api.model.Admin;
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
	public Result adminLogin(Admin admin) {
		log.debug("서비스!");
		Admin adminInfo = adminDao.selectAdmin(admin);
		
		Result result = new Result();
		if (adminInfo != null) {
			result.setMessage(adminInfo.getPwd());
		}
		
		return result;
	
	}
}