package com.example.beautydiary.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;


@Entity
@Data
@Table(name="service_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="full_name")
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private Time time;
    private String phoneNumber;
    @OneToOne
    private Beautician beautician;


}
