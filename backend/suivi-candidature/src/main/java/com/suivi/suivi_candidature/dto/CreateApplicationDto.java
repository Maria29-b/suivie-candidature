package com.suivi.suivi_candidature.dto;

import lombok.Data;

@Data
public class CreateApplicationDto {
    private String company;
    private String title;
    private String description;
    // ISO date string yyyy-MM-dd
    private String appliedDate;
    // expected values like PENDING, IN_PROCESS, ACCEPTED, REJECTED
    private String status;
}
