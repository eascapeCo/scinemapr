package com.eascapeco.scinemapr.bo.service;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.AdminToken;
import com.eascapeco.scinemapr.api.model.RefreshToken;
import com.eascapeco.scinemapr.api.service.admin.AdminService;
import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTAuthenticationService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AdminService adminService;

    public AdminToken getTokens(Admin chkAdm) {
        String access_token = null;
        String refresh_token = null;
        String expires_in = null;
        String errorCode = "00"; // success
        String errorMessage = "SUCCESS";

        try {
            access_token = jwtTokenProvider.generateToken(chkAdm);
            refresh_token = jwtTokenProvider.genRefreshToken(chkAdm);
//            expires_in = jwtTokenProvider.getExpiresIn(access_token);

            if (refresh_token != null && !refresh_token.isEmpty()) {

                RefreshToken tkn = new RefreshToken();
                tkn.setAdmNo(((Admin) chkAdm).getAdmNo());
                tkn.setRefreshToken(refresh_token);

//                Refresh Token 저장
                adminService.insertRefreshToken(tkn);
            } else {
                throw new BadCredentialsException("INVALID_CREDENTIALS");
            }
        } catch (DisabledException e) {
            errorCode = "107";
            errorMessage = "USER_DISABLED";
        } catch (BadCredentialsException e) {
            errorCode = "107";
            errorMessage = "INVALID_CREDENTIALS";
        } catch (UsernameNotFoundException unfe) {
            errorCode = "99";
            errorMessage = unfe.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = "502";
            errorMessage = e.getMessage();
        }
        // 00 성공, 107 파라미터 오류, 502 accessToken 발급 오류, 99 알수 없는 오류
        return new AdminToken(access_token, refresh_token, expires_in, errorCode, errorMessage);
    }
}