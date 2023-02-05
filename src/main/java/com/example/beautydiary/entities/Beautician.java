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
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String location;
    @ManyToOne
    private Category category;
    private String password;
    @Column(name = "profile_picture_URL")
    private String profilePictureURL;
    private String aboutMe;

    @CreationTimestamp
    private java.sql.Timestamp createdAt;
    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

}
