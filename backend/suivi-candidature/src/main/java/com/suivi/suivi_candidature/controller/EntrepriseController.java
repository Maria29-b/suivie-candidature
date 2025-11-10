package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Entreprise;
import com.suivi.suivi_candidature.service.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @GetMapping
    public ResponseEntity<List<Entreprise>> getAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getById(@PathVariable String id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Entreprise> create(@RequestBody Entreprise entreprise) {
        Entreprise created = entrepriseService.create(entreprise);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entreprise> update(@PathVariable String id, @RequestBody Entreprise entreprise) {
        return ResponseEntity.ok(entrepriseService.update(id, entreprise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
