package com.example.beautydiary.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "beauty_categories")
public class Category {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
