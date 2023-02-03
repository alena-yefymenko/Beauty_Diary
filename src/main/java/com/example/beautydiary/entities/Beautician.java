package com.example.beautydiary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Beautician {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String location;
    private String category;
    private String password;
    private String profilePictureURL;
    private java.sql.Timestamp createdAt;
    @UpdateTimestamp
    private java.sql. Timestamp updatedAt;

}
