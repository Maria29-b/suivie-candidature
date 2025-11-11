package com.suivi.suivi_candidature.controller;

import com.suivi.suivi_candidature.entity.Postuler;
import com.suivi.suivi_candidature.entity.PostulerId;
import com.suivi.suivi_candidature.service.PostulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulations")
@RequiredArgsConstructor
public class PostulerController {

    private final PostulerService postulerService;

    @GetMapping
    public ResponseEntity<List<Postuler>> getAll() {
        return ResponseEntity.ok(postulerService.findAll());
    }

    // accéder à une postulation via les 3 ids dans le path
    @GetMapping("/{idEnt}/{idUser}/{idStatut}")
    public ResponseEntity<Postuler> getById(@PathVariable String idEnt,
                                            @PathVariable String idUser,
                                            @PathVariable String idStatut) {
        PostulerId id = new PostulerId();
        id.setIdEnt(idEnt);
        id.setIdUser(idUser);
        id.setIdStatut(idStatut);
        return ResponseEntity.ok(postulerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Postuler> create(@RequestBody Postuler postuler) {
        return ResponseEntity.ok(postulerService.create(postuler));
    }

    @PutMapping("/{idEnt}/{idUser}/{idStatut}")
    public ResponseEntity<Postuler> update(@PathVariable String idEnt,
                                          @PathVariable String idUser,
                                          @PathVariable String idStatut,
                                          @RequestBody Postuler postuler) {
        PostulerId id = new PostulerId();
        id.setIdEnt(idEnt);
        id.setIdUser(idUser);
        id.setIdStatut(idStatut);
        return ResponseEntity.ok(postulerService.update(id, postuler));
    }

    @DeleteMapping("/{idEnt}/{idUser}/{idStatut}")
    public ResponseEntity<Void> delete(@PathVariable String idEnt,
                                       @PathVariable String idUser,
                                       @PathVariable String idStatut) {
        PostulerId id = new PostulerId();
        id.setIdEnt(idEnt);
        id.setIdUser(idUser);
        id.setIdStatut(idStatut);
        postulerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
