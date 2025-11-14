package com.suivi.suivi_candidature.dto;

import lombok.Data;

@Data
public class CreatePostulationDto {
    private String offerId;
    private String userId;
    private String appliedDate; // yyyy-MM-dd
    private String status;
    private String company;
    private String title;
    private String description;
    private String lienSource;
}
