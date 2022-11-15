package com.garanti.controllers;

import com.garanti.props.Admin;
import com.garanti.services.LoginService;
import com.garanti.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Controller
@RequiredArgsConstructor
@Validated
public class LoginController {

    final LoginService loginService;

    @GetMapping("/")
    public String login() {
        String sha = Util.sha1("12345");
        System.out.println(sha);
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin( @Valid Admin admin, BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors() ) {
            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println(error.getField() +" "+ error.getDefaultMessage() );
            });
        }else {
            boolean status = loginService.login(admin.getEmail(), admin.getPassword());
            System.out.println("Login: " + status);
            model.addAttribute("email", admin.getEmail());
        }

        return "login";
    }

}
