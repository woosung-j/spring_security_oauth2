package com.example.login.config.security.oauth;

import com.example.login.domain.SocialUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값을 변환해야 한다.
    // registrationId : 현재 로그인 진행 중인 서비스를 구분하는 코드
    // userNameAttributeName : ???
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if(registrationId.equals("kakao")) {
            return ofKakao(userNameAttributeName, attributes);
        } else if(registrationId.equals("naver")) {
            return ofNaver(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        return new OAuthAttributes(
                attributes,
                userNameAttributeName,
                (String) profile.get("nickname"),
                (String) kakao_account.get("email"));
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return new OAuthAttributes(
                attributes,
                userNameAttributeName,
                (String) response.get("name"),
                (String) response.get("email"));
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes(
                attributes,
                userNameAttributeName,
                (String) attributes.get("name"),
                (String) attributes.get("email"));
    }

    public SocialUser toEntity() {
        int idx = email.indexOf("@");
        String nickname = email.substring(0, idx-4) + "****" + email.substring(idx + 1, email.length() - 4);

        return new SocialUser(name, email, nickname, "ROLE_USER");
    }
}
