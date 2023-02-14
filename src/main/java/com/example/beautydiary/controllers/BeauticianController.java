package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.Reservation;
import com.example.beautydiary.services.BeauticianService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@Controller
public class BeauticianController {
    private BeauticianService beauticianService;


    public BeauticianController(BeauticianService beauticianService) {
        this.beauticianService = beauticianService;
    }

    @GetMapping("/categories/{categoryId}/beauticians")
    public String findAllByCategoryId(@PathVariable("categoryId") Long categoryId, Model model){
        List<Beautician> beauticians = beauticianService.findAllByCategoryId(categoryId);
        model.addAttribute("beauticians", beauticians);
        return "beauticians";
    }

    @GetMapping("/beauticians/{id}")
    public String viewBeauticianProfile(@PathVariable("id") Long id, Model model) {
        Beautician beautician = beauticianService.getById(id);
        model.addAttribute("beautician", beautician);
        var reservation = new Reservation();
        reservation.setBeautician(beautician);
        model.addAttribute("reservation", reservation);
        return "beautician";
    }

}
