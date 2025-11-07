package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "id_user", length = 50)
    private String idUser;

    @Column(name = "nom_user", nullable = false, length = 50)
    private String nomUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv", nullable = false)
    private Cv cv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivation", referencedColumnName = "id_motivation", nullable = false)
    private Motivation motivation;

    @OneToMany(mappedBy = "user")
    private List<Candidater> candidaters = new ArrayList<>();
}