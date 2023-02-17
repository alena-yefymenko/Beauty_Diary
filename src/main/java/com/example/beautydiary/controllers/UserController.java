package com.example.beautydiary.controllers;


import com.example.beautydiary.entities.User;
import com.example.beautydiary.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegistrationPage(){
        return "register";
    }

    @PostMapping("/register")
    public String handleUserRegistration(User user, Model model){
        try {
           userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("message","signup_failed");
            model.addAttribute("error",e.getMessage());
            model.addAttribute("user", user);
            return "register";
        }

        return "redirect:login?message=signup_success";
    }

    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(name = "message",required = false)String message,
            Model model) {
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleUserLogin(User user, HttpServletResponse response, Model model){
        try {
            User loggedInUser = userService.verifyUser(user);
            Cookie cookie= new Cookie("userId",loggedInUser.getId().toString());
            response.addCookie(cookie);
            response.addCookie(new Cookie("userIsLoggedIn","true"));
            model.addAttribute("user", loggedInUser);
            if(Objects.equals(loggedInUser.getUserType(), "beautician")) {
                return "redirect:/master-account/"+loggedInUser.getId();
            }else {
                return "/home";
            }
        }catch (Exception e){
            return "redirect:login?message=login_failed&error="+ e.getMessage();
        }

    }

}
