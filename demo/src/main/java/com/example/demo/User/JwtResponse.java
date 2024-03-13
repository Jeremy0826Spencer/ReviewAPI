package com.example.demo.User;

public class JwtResponse {
    private final String token;
    private final String type = "Bearer";

    private String redirectUrl; // Suggested redirect URL
    private String username = "";
    private final String email;
    private String role = null;

    public JwtResponse(String token, String redirectUrl, String email) {
        this.token = token;
        this.redirectUrl = redirectUrl;
        this.email = email;
    }

    public JwtResponse(String token, String username, String email, String role) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(String token) {
        this.token = token;
        this.email = "";
        this.username = "";
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
