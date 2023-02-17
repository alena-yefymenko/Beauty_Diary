package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.services.BeauticianService;
import com.example.beautydiary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private BeauticianService beauticianService;

    @Autowired
    public ProfileController(BeauticianService beauticianService) {
        this.beauticianService = beauticianService;
    }

    @GetMapping("profile/{userId}")
    public String displayProfile(@PathVariable Long userId, Model model,
                                 @CookieValue(value = "userId") String userIdFromCookie) {
        try {
            System.out.println(userIdFromCookie);
           // model.addAttribute("user", userService.findUserById(Long.valueOf(userIdFromCookie)));
            return "profile";
        } catch (Exception e) {
            return "redirect:login?message=user_not_found";
        }

    }

    @PostMapping("profile/{userId}")
    public String createProfile(@PathVariable Long userId) {
        return "redirect:/profile/" + userId;
    }
}
