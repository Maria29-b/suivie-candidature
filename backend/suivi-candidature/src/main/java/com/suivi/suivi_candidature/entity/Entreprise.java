package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Entreprise")
@Data
public class Entreprise {

    @Id
    @Column(name = "Id_ent", length = 50)
    private String idEnt;

    @Column(name = "nom_ent", nullable = false, length = 50)
    private String nomEnt;

    @Column(name = "site_web", nullable = false, length = 50)
    private String siteWeb;

    @Column(name = "adresse", nullable = false, length = 50)
    private String adresse;

    @Column(name = "contact", nullable = false, length = 50)
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_offre", referencedColumnName = "id_offre")
    private Offre offre;

    @OneToMany(mappedBy = "entreprise")
    private List<Candidater> candidaters = new ArrayList<>();
}