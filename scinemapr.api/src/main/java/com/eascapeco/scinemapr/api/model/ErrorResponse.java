package com.eascapeco.scinemapr.api.model;

public class ErrorResponse {
    private String code;

    private String description;

    private String detail;

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ErrorResponse(String code, String description, String detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
