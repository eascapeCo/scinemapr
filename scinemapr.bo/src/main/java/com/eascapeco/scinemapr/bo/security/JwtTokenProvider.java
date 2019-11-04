package com.eascapeco.scinemapr.bo.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.service.admin.AdminService;

@Component
public class JwtTokenProvider {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    // static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 토큰을 생성하는 메서드
     * 
     * @param id
     * @param pwd
     * @return
     */
    public Optional<String> createJwtToken(String id, String pwd) {
        pwd = "1234";
        Admin param = new Admin();
        param.setId("admin");
        
        Admin findAdmin = adminService.getAdmin(param);
        
        System.out.println(findAdmin.getPwd());
        System.out.println(passwordEncoder.matches(pwd, findAdmin.getPwd()));
        
        // 비밀번호 일치 검사
        if (!passwordEncoder.matches(pwd, findAdmin.getPwd())) {
            throw new RuntimeException();
        }
        
        // 계정 잠김 여부 추가 필요
        
        // 토큰 생성
        String token = JWT.create()
                .withSubject(findAdmin.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .sign(Algorithm.HMAC256("secret"));
        
        System.out.println(token);
        
        return Optional.of(token);
    }
    
    /**
     * JWT 토큰으로 인증 정보를 조회
     * 
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        return null;
    }
}