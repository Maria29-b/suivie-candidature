package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.dto.CreateApplicationDto;
import com.suivi.suivi_candidature.entity.*;
import com.suivi.suivi_candidature.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationsController {

    private final EntrepriseRepository entrepriseRepository;
    private final OffreRepository offreRepository;
    private final StatutRepository statutRepository;
    private final PostulerRepository postulerRepository;
    private final CandidatRepository candidatRepository;

    /**
     * Create an application from the frontend form. This will:
     * - find or create Entreprise by name
     * - create an Offre for the given title/description
     * - create an empty Statut entry
     * - create a Postuler linking the current authenticated user and entreprise/statut
     *
     * Note: files (CV/cover) are not handled here yet.
     */
    @PostMapping
    public ResponseEntity<Postuler> createApplication(@RequestBody CreateApplicationDto dto) {
        // find or create entreprise
        Entreprise entreprise = entrepriseRepository.findByNomEnt(dto.getCompany())
                .orElseGet(() -> {
                    Entreprise e = new Entreprise();
                    e.setNomEnt(dto.getCompany());
                    e.setSiteWeb("");
                    e.setAdresse("");
                    e.setContact("");
                    return entrepriseRepository.save(e);
                });

        // create offre
        Offre offre = new Offre();
        offre.setTitrePoste(dto.getTitle());
        offre.setDescription(dto.getDescription() == null ? "" : dto.getDescription());
        offre.setLienSource("");
        offre.setEntreprise(entreprise);
        offre = offreRepository.save(offre);

        // create statut (minimal)
        Statut statut = new Statut();
        statut.setDateEntretien(null);
        statut = statutRepository.save(statut);

        // determine current user pseudo from auth context and lookup Candidat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).build();
        }
        String pseudo = auth.getName();
        Candidat candidat = candidatRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable: " + pseudo));

        // create postuler entry (composite key fields)
        Postuler p = new Postuler();
        p.setIdEnt(entreprise.getIdEnt());
        p.setIdUser(candidat.getIdUser());
        p.setIdStatut(statut.getIdStatut());
        if (dto.getAppliedDate() != null && !dto.getAppliedDate().isBlank()) {
            p.setDateCandidature(LocalDate.parse(dto.getAppliedDate()));
        } else {
            p.setDateCandidature(LocalDate.now());
        }

        Postuler saved = postulerRepository.save(p);

        return ResponseEntity.ok(saved);
    }
}
