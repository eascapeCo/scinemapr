package com.eascapeco.scinemapr.api.model;

public class RefreshToken {
    private String id;
    private String refreshToken;

    public String getId(String username) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
