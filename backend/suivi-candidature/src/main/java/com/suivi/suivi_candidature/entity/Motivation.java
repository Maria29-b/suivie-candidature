package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "motivation")
public class Motivation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_motivation", length = 50)
    private String idMotivation;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Candidat candidat;
}
