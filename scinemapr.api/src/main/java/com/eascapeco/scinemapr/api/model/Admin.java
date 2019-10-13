package com.eascapeco.scinemapr.api.model;

import java.io.Serializable;

/**
 * 관리자 정보 model
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
@SuppressWarnings("serial")
public class Admin implements /* UserDetails, */ Serializable{
	
    private Integer no;			// 관리자 번호
    private String id;			// 아이디
    private String pwd;			// 비밀번호
    private String regDate;		// 생성일
    private String modDate;		// 수정일
    private Integer modNo;		// 수정자 
    private String roleName;	// 권한
    //private Collection<GrantedAuthority> authorities;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public Integer getModNo() {
        return modNo;
    }

    public void setModNo(Integer modNo) {
        this.modNo = modNo;
    }

    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	// Admin 모델 복사
    public void CopyData(Admin param)
    {
        this.no = param.getNo();
        this.id = param.getId();
        this.pwd = param.getPwd();
        this.regDate = param.getRegDate();
        this.modDate = param.getModDate();
        this.modNo = param.getModNo();
    }
/*
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getId();
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
*/
}
