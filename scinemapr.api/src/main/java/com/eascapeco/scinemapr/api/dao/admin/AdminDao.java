package com.eascapeco.scinemapr.api.dao.admin;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;


/**
 * 관리자 dao
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
//@Repository
public class AdminDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public Admin selectAdmin(Admin admin){
		return sqlSession.selectOne("Admin.selectAdmin", admin);
	}
	
	public void insertRefreshToken(AdminToken adminTkn){
	    sqlSession.insert("Admin.insertRefreshToken", adminTkn);
	};
	
}
