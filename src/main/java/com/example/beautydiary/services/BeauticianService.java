package com.example.beautydiary.services;

import com.example.beautydiary.repositories.BeauticianRepository;
import org.springframework.stereotype.Service;

@Service
public class BeauticianService {
    private  BeauticianRepository beauticianRepository;

    public BeauticianService(BeauticianRepository beauticianRepository) {
        this.beauticianRepository = beauticianRepository;
    }
}
