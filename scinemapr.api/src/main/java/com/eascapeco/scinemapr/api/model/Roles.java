package com.eascapeco.scinemapr.api.model;

public class Roles extends BaseModel {

    private int rolNo;
    private String rolNm;
    private String regDate;
    private int regNo;
    private String modDate;
    private int modNo;

    public int getRolNo() {
        return rolNo;
    }

    public void setRolNo(int rolNo) {
        this.rolNo = rolNo;
    }

    public String getRolNm() {
        return rolNm;
    }

    public void setRolNm(String rolNm) {
        this.rolNm = rolNm;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getRegNo() { return regNo; }

    public void setRegNo(int regNo) { this.regNo = regNo; }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public int getModNo() {
        return modNo;
    }

    public void setModNo(int modNo) {
        this.modNo = modNo;
    }
}
