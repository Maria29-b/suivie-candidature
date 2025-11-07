package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cv")
@Data
public class Cv {

    @Id
    @Column(name = "id_cv", length = 50)
    private String idCv;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "cv")
    private List<User> users = new ArrayList<>();
}