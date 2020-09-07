package com.eascapeco.scinemapr.api.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 응답 결과를 저장하는 model
 * 
 * @author jaehankim
 * @date 2019. 10. 10
 */
public class Result {
    private String code;
    private String message;
    private Map<String, Object> info;
    
    public Result() {
        // TODO Auto-generated constructor stub
    }
    
    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(String key, Object value) {
        if (this.info == null) {
            this.info = new HashMap<>();
        }
        this.info.put(key, value);
    }
}
