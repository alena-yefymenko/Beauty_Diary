package com.example.beautydiary.controllers;


import com.example.beautydiary.entities.User;
import com.example.beautydiary.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String handleUserRegistration(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("message", "signup_failed");
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
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
            Cookie userIdCookie= new Cookie("userId",loggedInUser.getId().toString());
            userIdCookie.setPath("/");
            userIdCookie.setMaxAge(3600); // 1 hour

            Cookie userIsLoggedInCookie= new Cookie("userIsLoggedIn","true");
            userIsLoggedInCookie.setPath("/");
            userIsLoggedInCookie.setMaxAge(3600); // 1 hour

            response.addCookie(userIdCookie);
            response.addCookie(userIsLoggedInCookie);

            model.addAttribute("user", loggedInUser);
            if (Objects.equals(loggedInUser.getUserType(), "beautician")) {
                return "redirect:/master-account/" + loggedInUser.getId();
            } else {
                return "redirect:/home";
            }
        }catch (Exception e){
            return "redirect:login?message=login_failed&error="+ e.getMessage();
        }
    }

}
