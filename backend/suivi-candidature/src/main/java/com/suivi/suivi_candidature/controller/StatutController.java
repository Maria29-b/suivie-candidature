package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Statut;
import com.suivi.suivi_candidature.service.StatutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuts")
@RequiredArgsConstructor
public class StatutController {

    private final StatutService statutService;

    @GetMapping
    public ResponseEntity<List<Statut>> getAll() {
        return ResponseEntity.ok(statutService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statut> getById(@PathVariable String id) {
        return ResponseEntity.ok(statutService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Statut> create(@RequestBody Statut statut) {
        return ResponseEntity.ok(statutService.create(statut));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statut> update(@PathVariable String id, @RequestBody Statut statut) {
        return ResponseEntity.ok(statutService.update(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        statutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
