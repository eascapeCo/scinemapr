package com.eascapeco.scinemapr.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 관리자 정보 model
 *
 * @author jaehankim
 * @Date 2019. 10. 10
 */
@SuppressWarnings("serial")
@Getter @Setter @ToString
public class Admin implements UserDetails, Serializable{

    private Integer admNo;		    // 관리자 번호
    private String  id;			    // 아이디
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String  pwd;		    // 비밀번호
    private String  regDate;	    // 아이디 등록일자
    private int regNo;          // 아이디 등록자 번호
    private String  modDate;	    // 최종 수정일자
    private int modNo;		    // 최종 수정자 번호
    private boolean pwdExpd;        // 비밀번호 갱신 여부
    private String[] roles;         // Roles
    private String roleName;	    // 권한
    private Collection<GrantedAuthority> authorities;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return getPwd();
    }

    @Override
    public String getUsername() {
        return getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
