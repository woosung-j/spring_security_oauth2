package com.example.login.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class SocialUser {
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String role = "ROLE_USER";

    @Builder
    public SocialUser(String name, String email, String nickname, String role) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public SocialUser update(String name) {
        this.name = name;

        return this;
    }
}
