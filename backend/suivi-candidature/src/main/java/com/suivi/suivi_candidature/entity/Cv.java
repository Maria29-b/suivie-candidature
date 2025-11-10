package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cv")
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cv", length = 50)
    private String idCv;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Candidat candidat;
}
