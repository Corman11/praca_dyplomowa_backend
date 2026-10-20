package org.example.backend_praca_dyplomowa.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @OneToOne
    private User user_author;

    @CreationTimestamp
    @Column( name = "account_creation_date",
            nullable = false,
            updatable = false)
    private LocalDateTime account_creationDate;
}
