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
public class AdminToken implements UserDetails, Serializable{
	
    private Integer adm_no;			// 관리자 번호
    private String id;			// 아이디
    private String tkn;       // 토큰

    private Collection<GrantedAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getId();
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    public Integer getAdm_no() {
        return adm_no;
    }

    public void setAdm_no(Integer adm_no) {
        this.adm_no = adm_no;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }
}
