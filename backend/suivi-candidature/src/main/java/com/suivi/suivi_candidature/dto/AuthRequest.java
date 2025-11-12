package com.suivi.suivi_candidature.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String pseudo;
    private String password;
}