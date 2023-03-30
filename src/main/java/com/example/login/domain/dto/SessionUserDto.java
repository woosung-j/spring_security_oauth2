package com.example.login.domain.dto;

import com.example.login.domain.SocialUser;
import lombok.Getter;

@Getter
public class SessionUserDto {
    private String name;
    private String email;
    private String nickname;
    private String role;

    public SessionUserDto(SocialUser socialUser) {
        this.name = socialUser.getName();
        this.email = socialUser.getEmail();
        this.nickname = socialUser.getNickname();
        this.role = socialUser.getRole();
    }
}
