package com.suivi.suivi_candidature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String pseudo;
    // ID du candidat (optionnel) renvoyé pour le frontend
    private String idUser;
}