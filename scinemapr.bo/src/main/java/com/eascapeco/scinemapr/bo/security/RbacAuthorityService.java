package com.eascapeco.scinemapr.bo.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
            log.info("tete {}", loginUser.getAuthorities().stream().anyMatch(s -> uriChecking(s, request.getMethod(), request.getRequestURI())));
            hasPermission = true;
            return hasPermission;
        }

        return hasPermission;
    }
    
    public boolean uriChecking(GrantedAuthority grantedAuthority, String method, String uri) {
        String authRolNm = grantedAuthority.getAuthority();
        log.info("method {} / uri {} / auth {}", method, uri, authRolNm);
        
        List<MenuRoles> urlList = rolesMapper.selectRoleMenus(authRolNm);
        return grantedAuthority.toString().equals("ADMIN2");
    }
}
