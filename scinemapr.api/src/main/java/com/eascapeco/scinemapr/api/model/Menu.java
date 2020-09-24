package com.eascapeco.scinemapr.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

    private static final long serialVersionUID = 2805850376459412143L;

    private Integer mnuNo;
    @Min(0)
    private int preMnuNo;
    // @NotBlank(message = "메뉴명은 필수 입니다.")
    private String mnuName;
    @Min(0) @Max(3)
    private int mnuLv;
    // @NotBlank(message = "URL은 필수 입니다.")
    private String urlAdr;
    private boolean useYn;
    private boolean dpYn;
    private int dpSequence;
    private String regDate;
    private int regNo;
    private String modDate;
    private int modNo;
    private List<Menu> subMenus;
    @JsonIgnore
    private Integer admNo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createType;

    public Integer getMnuNo() {
        return mnuNo;
    }

    public void setMnuNo(Integer mnuNo) {
        this.mnuNo = mnuNo;
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

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public Integer getAdmNo() {
        return admNo;
    }

    public void setAdmNo(Integer admNo) {
        this.admNo = admNo;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public boolean isDpYn() {
        return dpYn;
    }

    public void setDpYn(boolean dpYn) {
        this.dpYn = dpYn;
    }

    public int getDpSequence() {
        return dpSequence;
    }

    public void setDpSequence(int dpSequence) {
        this.dpSequence = dpSequence;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mnuNo=" + mnuNo +
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
