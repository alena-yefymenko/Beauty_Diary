package com.example.beautydiary.services;

import com.example.beautydiary.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private  ReservationRepository reservationRepository;git

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
