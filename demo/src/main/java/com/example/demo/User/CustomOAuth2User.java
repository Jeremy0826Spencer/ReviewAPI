package com.example.demo.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private Map<String, Object> attributes;

    // Additional fields for user data, adjust as needed
    private String id;
    private String username;
    private String email;

    public CustomOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.id = (String) attributes.get("id"); // Adjust the attribute name
        this.username = (String) attributes.get("username"); // Adjust the attribute name
        this.email = (String) attributes.get("email"); // Adjust the attribute name
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can return a collection of GrantedAuthority here if needed.
        // For example, if you have roles or authorities associated with the user.
        return null;
    }

    @Override
    public String getName() {
        // Return the user's name, usually an email or username.
        return email; // Assuming email is used as the username
    }

    // Additional getters and setters for user fields, e.g., id, username, email
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // You can add more methods and customize the class based on your requirements.
}
