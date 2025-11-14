package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offre")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_offre", length = 50)
    private String idOffre;

    @Column(name = "titre_poste", nullable = false, length = 50)
    private String titrePoste;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "lien_source", nullable = false, length = 50)
    private String lienSource;

    @ManyToOne
    @JoinColumn(name = "Id_ent", nullable = false)
    private Entreprise entreprise;
}
