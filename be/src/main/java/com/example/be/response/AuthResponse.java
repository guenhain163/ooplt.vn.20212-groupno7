package com.example.be.response;

public class AuthResponse {
    private String email;
    private String accessToken;
    private String tokenType;
    private Long expiresIn ;

    public AuthResponse() { }

    public AuthResponse(String email, String accessToken, Long expiresIn) {
        this.email = email;
        this.accessToken = accessToken;
        this.tokenType = "bearer";
        this.expiresIn = expiresIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
