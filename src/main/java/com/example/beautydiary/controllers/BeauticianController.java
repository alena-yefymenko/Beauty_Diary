package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.services.BeauticianService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/beauticians/{categoryId}")
    public String findAllByCategoryId(@PathVariable("categoryId") Long categoryId , Model model){
        List<Beautician> beauticians = beauticianService.findAllByCategoryId(categoryId);
        model.addAttribute("beauticians", beauticians );
        return "beauticians";
    }
}
