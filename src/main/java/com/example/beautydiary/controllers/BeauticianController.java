package com.example.beautydiary.controllers;

import com.example.beautydiary.services.BeauticianService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeauticianController {
 private BeauticianService beauticianService;

    public BeauticianController(BeauticianService beauticianService) {
        this.beauticianService = beauticianService;
    }

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }
}
