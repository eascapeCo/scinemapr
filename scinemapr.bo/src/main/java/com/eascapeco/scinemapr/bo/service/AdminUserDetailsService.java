package com.eascapeco.scinemapr.bo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eascapeco.scinemapr.api.dao.admin.AdminMapper;
import com.eascapeco.scinemapr.api.model.Admin;
import org.springframework.stereotype.Service;

/**
 * 
 * UserDetailsService 구현체
 * 
 * @date 2019. 11. 01
 * @author jaehankim
 *
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminMapper adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin adminInfo = adminDao.selectAdmin(username);
        adminInfo.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(adminInfo.getRoleName()));
        return adminInfo;
    }
    
}
