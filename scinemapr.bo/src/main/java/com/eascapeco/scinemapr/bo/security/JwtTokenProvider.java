package com.eascapeco.scinemapr.bo.security;

import java.io.Serializable;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenProvider implements Serializable {

    static final long JWT_TOKEN_EXP = 1 * (30 * 60); // 30 mins
    static final long JWT_REFRESH_TOKEN_EXP = 30 * (60 * 60 * 24); // 30 days

//    @Value("${jwt.secret}")
    private String secret = "escapeCoperation";

//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    /**
     * 토큰을 생성하는 메서드
     * 
     * @param id
     * @return
     */
    public String createJwtToken(String id) {

        // 토큰 생성
        String token = JWT.create().withSubject(id)
                          .withIssuedAt(new Date(System.currentTimeMillis()))
                          .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXP * 1000))
                          .sign(Algorithm.HMAC256(secret));

        return token;
    }
    
    /**
     * 리프레쉬 토큰 생성 메소드
     * 
     * @param id
     * @return Optional
     */
    public String refreshJwtToken(String id) {
//         RefreshToken 생성
        String refreshToken = JWT.create().withSubject(id)
                                          .withIssuedAt(new Date(System.currentTimeMillis()))
                                          .withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_EXP * 1000))
                                          .sign(Algorithm.HMAC256(secret));
        
        return refreshToken;
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

    public String getExpiresIn(String token) {
        return Long.toString(Math.abs(JWT.decode(token).getExpiresAt().getTime() - new Date(System.currentTimeMillis()).getTime() / 1000));
    }

//     retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token);
    }

    public String getClaimFromToken(String token) {
        return JWT.decode(token).getSubject();
//        return claimsResolver.apply(claims);
    }


}