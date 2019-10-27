package com.eascapeco.scinemapr.api.dao.admin;

import com.eascapeco.scinemapr.api.model.Admin;

/**
 * 관리자 mapper
 * 
 * @author jaehankim
 * @date 2019. 10. 20
 */
public interface AdminMapper {
	public Admin selectAdmin(Admin admin);
}
