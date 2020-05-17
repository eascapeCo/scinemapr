package com.eascapeco.scinemapr.api.model;

import java.io.Serializable;

public class Menu implements Serializable {

    private Integer mnuNo;
    private int rolNo;
    private int preMnuNo;
    private String mnuName;
    private int mnuLv;
    private String urlAdr;
    private boolean useYn;
    private String regDate;
    private int regNo;
    private String modDate;
    private int modNo;

    public Integer getMnuNo() {
        return mnuNo;
    }

    public void setMnuNo(Integer mnuNo) {
        this.mnuNo = mnuNo;
    }

    public int getRolNo() {
        return rolNo;
    }

    public void setRolNo(int rolNo) {
        this.rolNo = rolNo;
    }

    public int getPreMnuNo() {
        return preMnuNo;
    }

    public void setPreMnuNo(int preMnuNo) {
        this.preMnuNo = preMnuNo;
    }

    public String getMnuName() {
        return mnuName;
    }

    public void setMnuName(String mnuName) {
        this.mnuName = mnuName;
    }

    public int getMnuLv() {
        return mnuLv;
    }

    public void setMnuLv(int mnuLv) {
        this.mnuLv = mnuLv;
    }

    public String getUrlAdr() {
        return urlAdr;
    }

    public void setUrlAdr(String urlAdr) {
        this.urlAdr = urlAdr;
    }

    public boolean isUseYn() {
        return useYn;
    }

    public void setUseYn(boolean useYn) {
        this.useYn = useYn;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

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

    @Override
    public String toString() {
        return "Menu{" +
                "mnuNo=" + mnuNo +
                ", rolNo=" + rolNo +
                ", preMnuNo=" + preMnuNo +
                ", mnuName='" + mnuName + '\'' +
                ", mnuLv=" + mnuLv +
                ", urlAdr='" + urlAdr + '\'' +
                ", useYn=" + useYn +
                ", regDate='" + regDate + '\'' +
                ", regNo=" + regNo +
                ", modDate='" + modDate + '\'' +
                ", modNo=" + modNo +
                '}';
    }
}
