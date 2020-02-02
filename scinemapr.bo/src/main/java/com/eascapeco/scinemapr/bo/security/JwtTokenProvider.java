package com.eascapeco.scinemapr.bo.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
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
    public AdminToken createJwtToken(String id, String pwd) {
        Admin param = new Admin();
        param.setId(id);
        
        Admin findAdmin = adminService.getAdmin(param);
        
        AdminToken adminToken = new AdminToken();
        
        System.out.println(pwd);
        System.out.println(findAdmin.getPwd());
        System.out.println(passwordEncoder.matches(pwd, findAdmin.getPwd()));
        
        // 비밀번호 일치 검사
        if (!passwordEncoder.matches(pwd, findAdmin.getPwd())) {
            throw new RuntimeException();
        }
        
        
        // 계정 잠김 여부 추가 필요
        
        // 토큰 생성
        String token = JWT.create().withClaim("adminNo", findAdmin.getNo())
                                    .withClaim("name", findAdmin.getUsername())
                                    .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                                    .withIssuedAt(Date.from(LocalDateTime.now().plusSeconds(1800).atZone(ZoneId.systemDefault()).toInstant()))
                                    .sign(Algorithm.HMAC256("secret"));
        
        adminToken.setAdm_no(findAdmin.getNo());
        adminToken.setTkn(token);
        
        System.out.println(token);
        return adminToken;
    }
    
    /**
     * 리프레쉬 토큰 생성 메소드
     * 
     * @param admintoken
     * @param pwd
     * @return Optional
     */
    public Optional<String> refreshJwtToken(AdminToken admintoken) {
        
//         RefreshToken 생성
        String refreshToken = JWT.create().withClaim("adminNo", admintoken.getAdm_no())
                                          .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                                          .withExpiresAt(Date.from(LocalDateTime.now().plusDays(14).atZone(ZoneId.systemDefault()).toInstant()))
                                          .sign(Algorithm.HMAC256("secret"));
        
        admintoken.setTkn(refreshToken);
        
        adminService.insertRefreshToken(admintoken);

        return Optional.of(refreshToken);
    }
    
    /**
     * JWT 토큰으로 인증 정보를 조회
     * 
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(null, "", null);
    }
    
}