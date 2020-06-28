package com.eascapeco.scinemapr.api.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 관리자 정보 model
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
@SuppressWarnings("serial")
public class AdminToken implements Serializable{

    private static final long serialVersionUID = -8091879091924046844L;
    private final String access_token;
    private final String refresh_token;
    private final String errorCode;
    private final String errorMessage;

    public AdminToken(String access_token, String refresh_token, String errorCode,
                       String errorMessage) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.errorCode = errorCode; // 00 성공, 107 파라미터 오류, 502 accessToken 발급 오류, 99 알수 없는 오류
        this.errorMessage = errorMessage; // 00 성공, 107 파라미터 오류, 502 accessToken 발급 오류, 99 알수 없는 오류
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
