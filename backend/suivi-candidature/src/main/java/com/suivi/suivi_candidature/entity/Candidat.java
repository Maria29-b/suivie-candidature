package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user", length = 50)
    private String idUser;

    @Column(name = "nom_user", nullable = false, length = 50)
    private String nomUser;

    @OneToMany(mappedBy = "candidat")
    private List<Motivation> motivations;

    @OneToMany(mappedBy = "candidat")
    private List<Cv> cvs;

    @OneToMany(mappedBy = "candidat")
    private List<Postuler> postulations;
}
