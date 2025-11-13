package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Offre;
import com.suivi.suivi_candidature.service.OffreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offres")
@CrossOrigin(origins = "*") // autorise les appels depuis ton frontend React
public class OffreController {

    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    // 🔹 GET — toutes les offres
    @GetMapping
    public ResponseEntity<List<Offre>> getAllOffres() {
        return ResponseEntity.ok(offreService.getAllOffres());
    }

    // 🔹 GET — une offre par ID
    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable String id) {
        return offreService.getOffreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST — créer une offre
    @PostMapping
    public ResponseEntity<Offre> createOffre(@RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.createOffre(offre));
    }

    // 🔹 PUT — modifier une offre
    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(
            @PathVariable String id,
            @RequestBody Offre offreDetails
    ) {
        return ResponseEntity.ok(offreService.updateOffre(id, offreDetails));
    }

    // 🔹 DELETE — supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable String id) {
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }
}
