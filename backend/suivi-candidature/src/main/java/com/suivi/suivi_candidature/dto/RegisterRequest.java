package com.suivi.suivi_candidature.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String pseudo;
    private String password;
    private String nomUser;
}