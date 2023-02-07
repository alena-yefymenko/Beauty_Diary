package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Reservation;
import com.example.beautydiary.services.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/reservation")
    public String addReservation(@ModelAttribute Reservation reservation, HttpSession session) {
        reservationService.saveReservation(reservation);
        session.setAttribute("reservation", reservation);
        return "redirect:/reservation-result";
    }

    @GetMapping(path = "/reservation-result")
    public String showReservationResultPage(HttpSession session, Model model) {
        Reservation reservation =  (Reservation) session.getAttribute("reservation");
        model.addAttribute("reservation", reservation);
        return "reservation-result";
    }
}
