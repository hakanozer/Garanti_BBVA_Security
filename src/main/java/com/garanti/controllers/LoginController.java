package com.garanti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam( required = false ) String remember,
            Model model
    ) {
        model.addAttribute("email", email);
        return "login";
    }

}
