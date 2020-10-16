package com.eascapeco.scinemapr.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 클래스명: <code>BaseModel</code><br/><br/>
 *
 * 공통으로 사용하는 값을 가진 모델
 *
 * @since 2020. 10. 16.
 * @author JaeHan-Kim
 *
 */
public class BaseModel {
    
    private int page;
    
    private int limit;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getPage() {
        return this.page <= 0 ? 1 : this.page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getLimit() {
        return this.limit <= 0 ? 10 : this.limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getOffset() {
        return (this.getPage() - 1) * this.limit;
    }
}
