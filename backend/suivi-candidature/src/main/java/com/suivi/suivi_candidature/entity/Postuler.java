package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "postuler")
@IdClass(PostulerId.class)
public class Postuler {

    @Id
    @Column(name = "Id_ent", length = 50)
    private String idEnt;

    @Id
    @Column(name = "id_user", length = 50)
    private String idUser;

    @Id
    @Column(name = "id_statut", length = 50)
    private String idStatut;

    @Column(name = "date_candidature")
    private LocalDate dateCandidature;

    @ManyToOne
    @JoinColumn(name = "Id_ent", insertable = false, updatable = false)
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "id_statut", insertable = false, updatable = false)
    private Statut statut;
}
