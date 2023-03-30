package com.example.login.service;


import com.example.login.config.security.oauth.OAuthAttributes;
import com.example.login.domain.SocialUser;
import com.example.login.domain.dto.SessionUserDto;
import com.example.login.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);

        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        log.info("*************************************");
        log.info("registrationId");
        log.info(registrationId);
        log.info("userNameAttributerName");
        log.info(userNameAttributeName);

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        SocialUser socialUser = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUserDto(socialUser));

        log.info("*************************************");
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(socialUser.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private SocialUser saveOrUpdate(OAuthAttributes attributes) {
        SocialUser socialUser = userMapper.findUserByEmail(attributes.getEmail());

        if(socialUser == null) {
            socialUser = attributes.toEntity();
            userMapper.insertUserBySocialUser(socialUser);
        } else {
//            socialUser.update(attributes.getName());
            userMapper.updateNameById(socialUser);
        }

        return socialUser;
    }

    public SessionUserDto getSession() {
        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");

        return user;
    }

    public SocialUser getUser(String email) {
        return userMapper.findUserByEmail(email);
    }
}
