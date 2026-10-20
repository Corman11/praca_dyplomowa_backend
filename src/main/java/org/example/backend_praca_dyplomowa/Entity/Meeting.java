package org.example.backend_praca_dyplomowa.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int likes;
    private Date dateTime;

    @OneToMany
    private List<User> participants;

    // Getters and setters
}