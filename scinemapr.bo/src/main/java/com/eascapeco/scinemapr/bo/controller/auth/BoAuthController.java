package com.eascapeco.scinemapr.bo.controller.auth;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eascapeco.scinemapr.api.service.admin.AdminService;
import com.eascapeco.scinemapr.bo.service.AdminUserDetailsService;
import com.eascapeco.scinemapr.bo.service.JWTAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.Result;
import com.eascapeco.scinemapr.api.util.CookieUtils;
import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;

/**
 * 
 * Bo 관리자 인증 Controller
 * 
 * @date 2019. 11. 01
 * @author jaehankim
 *
 */

@RestController
public class BoAuthController {

    private final Logger log = LoggerFactory.getLogger(BoAuthController.class);

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;
    @Autowired
    private JWTAuthenticationService jwtAuthenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/admin/login")
    public ResponseEntity<?> login(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticate(admin.getId(), admin.getPassword());
        adminUserDetailsService.loadUserByUsername(admin.getId());
        return ResponseEntity.ok(jwtAuthenticationService.getTokens(admin.getId()));
    }

    // 인증
    private void authenticate(String username, String password) throws Exception {
        System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)));

    }
}
