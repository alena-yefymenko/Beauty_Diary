package com.example.beautydiary.services;

import com.example.beautydiary.entities.Reservation;
import com.example.beautydiary.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private  ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation){
      return reservationRepository.save(reservation);
    }




}
