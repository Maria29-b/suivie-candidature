package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "cv")
public class Cv {

    @Id
    @Column(name = "id_cv", length = 50, nullable = false, updatable = false)
    private String idCv = UUID.randomUUID().toString();

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDate dateCreation = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Candidat candidat;

    // Tu peux ajouter d'autres champs ici si nécessaire
    @Column(name = "titre")
    private String titre;

    @Column(name = "description", length = 1000)
    private String description;
}
