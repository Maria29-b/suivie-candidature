package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Candidat;
import com.suivi.suivi_candidature.service.CandidatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidats")
@RequiredArgsConstructor
public class CandidatController {

    private final CandidatService candidatService;

    @GetMapping
    public ResponseEntity<List<Candidat>> getAll() {
        return ResponseEntity.ok(candidatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getById(@PathVariable String id) {
        return ResponseEntity.ok(candidatService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Candidat> create(@RequestBody Candidat candidat) {
        return ResponseEntity.ok(candidatService.create(candidat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable String id, @RequestBody Candidat candidat) {
        return ResponseEntity.ok(candidatService.update(id, candidat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        candidatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
