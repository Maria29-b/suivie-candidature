package com.suivi.suivi_candidature.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class PostulerId implements Serializable {
    private String idEnt;
    private String idUser;
    private String idStatut;
}
