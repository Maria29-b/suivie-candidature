package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "motivation")
@Data
public class Motivation {

    @Id
    @Column(name = "id_motivation", length = 50)
    private String idMotivation;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "motivation")
    private List<User> users = new ArrayList<>();
}