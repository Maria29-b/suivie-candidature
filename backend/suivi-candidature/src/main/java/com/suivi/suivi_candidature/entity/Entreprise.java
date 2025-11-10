package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @OneToMany(mappedBy = "entreprise")
    private List<Offre> offres;

    @OneToMany(mappedBy = "entreprise")
    private List<Postuler> postulations;
}
