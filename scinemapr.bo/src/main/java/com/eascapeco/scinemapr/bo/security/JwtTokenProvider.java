package com.eascapeco.scinemapr.bo.security;

import java.io.Serializable;
import java.sql.PseudoColumnUsage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eascapeco.scinemapr.api.model.Admin;
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
    private final String secret = "escapeCoperation";

//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public String generateToken(Admin chkAdm) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", chkAdm.getId());
        claims.put("admNo", chkAdm.getAdmNo());
        claims.put("roles", chkAdm.getAuthorities());
        return createJwtToken(claims, chkAdm);
    }

    /**
     * 토큰을 생성하는 메서드
     * 
     * @param id
     * @param claims
     * @return
     */
    public String createJwtToken(Map<String, Object> claims, Admin admin) {

//        토큰 생성
        return JWT.create().withHeader(claims)
                           .withIssuedAt(new Date(System.currentTimeMillis()))
                           .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXP * 1000))
                           .sign(Algorithm.HMAC256(secret));
    }
    
    /**
     * 리프레쉬 토큰 생성 메소드
     * 
     * @param Admin
     * @return Optional
     */
    public String refreshJwtToken(Admin admin) {
//         RefreshToken 생성
        String refreshToken = JWT.create().withClaim("id", admin.getId())
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
        return getAllClaimsFromToken(token);
    }

    // for retrieveing any information from token we will need the secret key
    public String getAllClaimsFromToken(String token) {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
//                .withIssuer("aa22vv")
                .acceptExpiresAt(5 * 60)
                .build();

//        DecodedJWT jwt = verifier.verify(token);
        JWT jwt = new JWT();
        DecodedJWT dJwt = jwt.decodeJwt(token);

        System.out.println("token : " + token);
        System.out.println("JWT.decode(token) : " + dJwt.getHeaderClaim("id"));
        return dJwt.getHeaderClaim("id").toString();
    }
}