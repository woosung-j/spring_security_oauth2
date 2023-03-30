package com.example.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("")
public class UserController {
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) { return "home"; }

    @GetMapping("/anonymous")
    public String anonymous(HttpServletRequest request) { return "anonymous"; }

    @GetMapping("/auth")
    public String auth(HttpServletRequest request) {
        return "auth";
    }

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        return "admin";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        return "user";
    }

    @GetMapping("/userAdmin")
    public String userAdmin(HttpServletRequest request) {
        return "userAdmin";
    }

    @GetMapping("/denied")
    public String denied(HttpServletRequest request) {
        return "denied";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String uri = request.getHeader("Referer");
        if(uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";

    }
}
