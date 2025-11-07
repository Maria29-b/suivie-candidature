package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "candidater")
@Data
public class Candidater {

    @EmbeddedId
    private CandidaterId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEnt")
    @JoinColumn(name = "Id_ent_1")
    private Entreprise entreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUser")
    @JoinColumn(name = "id_user_1")
    private User user;

    @Column(name = "date_candidature")
    private LocalDate dateCandidature;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @Column(name = "date_entretien")
    private LocalDate dateEntretien;
}