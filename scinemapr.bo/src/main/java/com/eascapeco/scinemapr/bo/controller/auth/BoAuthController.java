package com.eascapeco.scinemapr.bo.controller.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.Result;
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
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/admin/login")
    public Result login(@RequestBody Admin admin) {
        log.debug("id {}", admin.getId());
        log.debug("pwd {}", admin.getPwd());

        Optional<String> token = jwtTokenProvider.createJwtToken(admin.getId(), admin.getPwd());

        Result result = new Result();
        if (token.isPresent()) {
            Optional<String> refreshToken = jwtTokenProvider.refreshJwtToken(admin);
            log.debug("token {}", token.get());
            result.setCode("200");
            result.setMessage("Login Success");
            result.setInfo("accessToken", token.get());
            result.setInfo("refreshToken", refreshToken.get());
        }
        return result;
    }
}
