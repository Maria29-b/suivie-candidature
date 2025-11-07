package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "statut")
public class Statut {

    @Id
    @Column(name = "id_statut", length = 50)
    private String idStatut;

    @Column(name = "date_entretien")
    private LocalDate dateEntretien;

    @OneToMany(mappedBy = "statut")
    private List<Postuler> postulations;
}
