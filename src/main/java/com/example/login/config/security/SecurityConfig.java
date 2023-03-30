package com.example.login.config.security;


import com.example.login.service.CustomOAuth2UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/", "/home").permitAll() // permitAll() 모두 접근 가능
                        .requestMatchers("/img/**", "/js/**", "/css/**").permitAll()
                        .requestMatchers("/auth").authenticated() // authenticated() 인증된 유저만 접근 가능
                        .requestMatchers("/anonymous", "/login").hasRole("ANONYMOUS") // .hasRole() 괄호 안에 있는 권한을 가진 유저만 접근 가능
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/userAdmin").hasAnyRole("USER", "ADMIN") // .hasAnyRole() 괄호 안에 있는 권한을 가진 모든 유저 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout // 로그아웃 설정
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .oauth2Login(login -> login // 소셜 로그인 설정
                        .loginPage("/login")
                        .successHandler(new CustomAuthSuccessHandler()) // 성공 핸들러 설정
                        .userInfoEndpoint() // 로그인 성공 이후의 설정 시작
                            .userService(customOAuth2UserService) // 설정을 customOAuth2UserService에서 처리
                )
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }
    
    // 접근 거부 오류처리 핸들러
    private AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }
}
