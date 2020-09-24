package com.eascapeco.scinemapr.bo.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eascapeco.scinemapr.bo.service.AdminUserDetailsService;
import com.eascapeco.scinemapr.bo.service.JWTAuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.eascapeco.scinemapr.api.model.Admin;
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
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/admin/login")
    public ResponseEntity<?> login(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticate(admin.getId(), admin.getPassword());

        Admin chkAdmin = new Admin();
//        널체크
        if (!StringUtils.isEmpty(admin.getId())) {
            chkAdmin = adminUserDetailsService.loadUserByUsername(admin.getId());
        }
        return ResponseEntity.ok(jwtAuthenticationService.getTokens(chkAdmin));
    }

//    인증
    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }
}
