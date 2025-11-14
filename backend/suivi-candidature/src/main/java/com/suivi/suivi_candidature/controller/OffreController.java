package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Offre;
import com.suivi.suivi_candidature.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;

    @GetMapping
    public ResponseEntity<List<Offre>> getAll() {
        return ResponseEntity.ok(offreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getById(@PathVariable String id) {
        return ResponseEntity.ok(offreService.findById(id));
    }
    
    // 
    @PostMapping("/")
    public ResponseEntity<Offre> create(@RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.create(offre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> update(@PathVariable String id, @RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.update(id, offre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        offreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}