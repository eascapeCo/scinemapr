package com.eascapeco.scinemapr.api.model;

import java.io.Serializable;

public class MenuRoles implements Serializable {

    private static final long serialVersionUID = -6439711725125265330L;

    private int rolNo;
    private int mnuNo;
    private String rolNm;
    private boolean mnuCreate;
    private boolean mnuRead;
    private boolean mnuUpdate;
    private boolean mnuDelete;

    public int getRolNo() {
        return rolNo;
    }
    public void setRolNo(int rolNo) {
        this.rolNo = rolNo;
    }
    public int getMnuNo() {
        return mnuNo;
    }
    public void setMnuNo(int mnuNo) {
        this.mnuNo = mnuNo;
    }
    public String getRolNm() {
        return rolNm;
    }
    public void setRolNm(String rolNm) {
        this.rolNm = rolNm;
    }
    public boolean isMnuCreate() {
        return mnuCreate;
    }
    public void setMnuCreate(boolean mnuCreate) {
        this.mnuCreate = mnuCreate;
    }
    public boolean isMnuRead() {
        return mnuRead;
    }
    public void setMnuRead(boolean mnuRead) {
        this.mnuRead = mnuRead;
    }
    public boolean isMnuUpdate() {
        return mnuUpdate;
    }
    public void setMnuUpdate(boolean mnuUpdate) {
        this.mnuUpdate = mnuUpdate;
    }
    public boolean isMnuDelete() {
        return mnuDelete;
    }
    public void setMnuDelete(boolean mnuDelete) {
        this.mnuDelete = mnuDelete;
    }
}
