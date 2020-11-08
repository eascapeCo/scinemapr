package com.eascapeco.scinemapr.bo.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.eascapeco.scinemapr.api.dao.role.RolesMapper;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.MenuRoles;

/**
 * RBAC(role-based access control) checking component
 * 
 * @author JaeHan-Kim
 * @since 2020.10.05
 *
 */
@Component
public class RbacAuthorityService {

    private final Logger log = LoggerFactory.getLogger(RbacAuthorityService.class);

    @Autowired
    private RolesMapper rolesMapper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        log.debug("rbac service");
        log.info("==============================");
        log.info("method {} / uri {} / url {}", request.getMethod(), request.getRequestURI(), request.getRequestURL());
        log.info("==============================");
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        log.info("object is admin --> {}", userInfo instanceof Admin);

        // 
        if (userInfo instanceof Admin) {
            Admin loginUser = (Admin) userInfo;
            // log.info("tete {}", loginUser.getAuthorities().stream().anyMatch(s -> uriChecking(s, request.getMethod(), request.getRequestURI())));
            hasPermission = loginUser.getAuthorities().stream().anyMatch(s -> uriChecking(s, request.getMethod(), request.getRequestURI()));
            // return hasPermission;
        }
        log.info("hasPermission -> {}", hasPermission);

        return true;
    }
    
    public boolean uriChecking(GrantedAuthority grantedAuthority, String method, String uri) {
        String authRolNm = grantedAuthority.getAuthority();
        log.info("method {} / uri {} / auth {}", method, uri, authRolNm);
        String[] arr = uri.split("/");
        log.info("{}", ArrayUtils.toString(arr));
        List<MenuRoles> urlList = rolesMapper.selectRoleMenus(authRolNm);

        return urlList.stream().anyMatch(menu -> StringUtils.equals(uri, menu.getUrlAdr()));
    }
}
