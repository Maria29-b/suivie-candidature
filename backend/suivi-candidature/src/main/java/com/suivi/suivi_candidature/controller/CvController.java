package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Cv;
import com.suivi.suivi_candidature.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
@RequiredArgsConstructor
public class CvController {

    private final CvService cvService;

    @GetMapping
    public ResponseEntity<List<Cv>> getAll() {
        return ResponseEntity.ok(cvService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cv> getById(@PathVariable String id) {
        return ResponseEntity.ok(cvService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cv> create(@RequestBody Cv cv) {
        return ResponseEntity.ok(cvService.create(cv));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cv> update(@PathVariable String id, @RequestBody Cv cv) {
        return ResponseEntity.ok(cvService.update(id, cv));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cvService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
