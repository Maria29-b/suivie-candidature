package com.suivi.suivi_candidature.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CandidaterId implements Serializable {

    @Column(name = "Id_ent_1", length = 50)
    private String idEnt;

    @Column(name = "id_user_1", length = 50)
    private String idUser;

    public CandidaterId() {}

    public CandidaterId(String idEnt, String idUser) {
        this.idEnt = idEnt;
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidaterId that = (CandidaterId) o;
        return Objects.equals(idEnt, that.idEnt) &&
               Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEnt, idUser);
    }
}