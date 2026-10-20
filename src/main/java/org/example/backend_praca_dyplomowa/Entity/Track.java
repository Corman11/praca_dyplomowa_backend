package org.example.backend_praca_dyplomowa.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int likes;


    private float rating;

    private LocalDateTime meeting_time;

    private String track_type;

    // Getters and setters
}