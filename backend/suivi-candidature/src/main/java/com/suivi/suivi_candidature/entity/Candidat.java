package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor 
@Builder
@AllArgsConstructor
@Entity
@Table(name = "candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user", length = 50)
    private String idUser;

    @Column(name = "nom_user", nullable = false, length = 50)
    private String nomUser;

    @Column(name = "pseudo", nullable = false, unique = true, length = 50)
    private String pseudo;

    @Column(name = "password", nullable = false, length = 255)
    private String password;
    

    @OneToMany(mappedBy = "candidat")
    private List<Motivation> motivations;

    @OneToMany(mappedBy = "candidat")
    private List<Cv> cvs;

    @OneToMany(mappedBy = "candidat")
    private List<Postuler> postulations;
}
