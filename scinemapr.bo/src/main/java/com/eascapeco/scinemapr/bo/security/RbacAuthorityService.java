package com.eascapeco.scinemapr.bo.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.eascapeco.scinemapr.api.model.Admin;

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
            hasPermission = true;
            return hasPermission;
        }

        return hasPermission;
    }
}
