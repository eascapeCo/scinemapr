package com.eascapeco.scinemapr.bo.service;

import com.eascapeco.scinemapr.api.service.roles.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eascapeco.scinemapr.api.dao.admin.AdminMapper;
import com.eascapeco.scinemapr.api.model.Admin;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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


    private final Logger log = LoggerFactory.getLogger(AdminUserDetailsService.class);

    @Autowired
    private AdminMapper adminDao;

    @Override
    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin adminInfo = adminDao.selectAdmin(username);
        adminInfo.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(adminInfo.getRoleName()));
//        adminInfo.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(adminInfo.getRoleName())));
        return adminInfo;
    }

}
