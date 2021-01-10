package com.eascapeco.scinemapr.bo.controller.admin;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.Result;
import com.eascapeco.scinemapr.api.model.Roles;
import com.eascapeco.scinemapr.api.service.admin.AdminService;
import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

/**
 *
 * Bo 관리자 인증 Controller
 *
 * @Date 2019. 11. 01
 * @author jaehankim
 *
 */

@RestController
@RequestMapping("/api")
public class BoAdminController {

    private final Logger log = LoggerFactory.getLogger(BoAdminController.class);

    @Autowired
    private JWTAuthenticationService jwtAuthenticationService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/admin")
    public List<Admin> getAdminList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return adminService.getAdminList();
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("userId : {}, Roles : {}, RequestHeader : {}", admin.getUsername(), admin.getRoles(), request.getHeader("Authorization"));

        Result result = new Result();
        if (adminService.dupChkUsername(admin.getUsername()) < 1) {

            String token = request.getHeader("Authorization").substring(7);
            adminService.insertAdmin(admin, jwtTokenProvider.getAdminNoFromToken(token));

            result.setCode(200);
            result.setMessage("success");
        } else {
            result.setCode(502);
            result.setMessage("Duplication Id");
        }
        return null;
    }

}
