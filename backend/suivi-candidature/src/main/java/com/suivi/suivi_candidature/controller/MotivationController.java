package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Motivation;
import com.suivi.suivi_candidature.service.MotivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motivations")
@RequiredArgsConstructor
public class MotivationController {

    private final MotivationService motivationService;

    @GetMapping
    public ResponseEntity<List<Motivation>> getAll() {
        return ResponseEntity.ok(motivationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motivation> getById(@PathVariable String id) {
        return ResponseEntity.ok(motivationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Motivation> create(@RequestBody Motivation motivation) {
        return ResponseEntity.ok(motivationService.create(motivation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motivation> update(@PathVariable String id, @RequestBody Motivation motivation) {
        return ResponseEntity.ok(motivationService.update(id, motivation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        motivationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
