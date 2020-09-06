package com.eascapeco.scinemapr.bo.controller.exception;

public enum ErrorCode {
    NOT_NULL("ERROR_CODE_0001","필수값이 누락되었습니다")
    , MIN_VALUE("ERROR_CODE_0002", "최소값보다 커야 합니다.")
    , MAX_VALUE("ERROR_CODE_0002", "최댁값보다 작아야 합니다.");

    private String code;
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
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
}
