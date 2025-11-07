package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offre")
@Data
public class Offre {

    @Id
    @Column(name = "id_offre", length = 50)
    private String idOffre;

    @Column(name = "titre_poste", nullable = false, length = 50)
    private String titrePoste;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Column(name = "lien_source", nullable = false, length = 50)
    private String lienSource;

    @OneToMany(mappedBy = "offre")
    private List<Entreprise> entreprises = new ArrayList<>();
}