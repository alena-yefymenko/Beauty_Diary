package com.example.beautydiary.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "beauticians")
public class Beautician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    private String phoneNumber;
    private String address;

    @ManyToOne
    private Category category;

    private String password;
    @Column(name = "profile_picture_URL")
    private String profilePictureURL;
    private String aboutMe;
    private String skills;
    @CreationTimestamp
    private java.sql.Timestamp createdAt;
    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;


    public String getShortAboutMe() {
        int maxLength = 100;
        if (aboutMe.length() <= maxLength) {
            return aboutMe;
        } else {
            return aboutMe.substring(0, maxLength) + "...";
        }
    }
}


