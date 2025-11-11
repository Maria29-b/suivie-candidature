package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Statut;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.StatutRepository;
import com.suivi.suivi_candidature.service.StatutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatutServiceImpl implements StatutService {

    private final StatutRepository statutRepository;

    @Override
    public List<Statut> findAll() {
        return statutRepository.findAll();
    }

    @Override
    public Statut findById(String id) {
        return statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statut introuvable: " + id));
    }

    @Override
    public Statut create(Statut statut) {
        return statutRepository.save(statut);
    }

    @Override
    public Statut update(String id, Statut statut) {
        Statut exist = findById(id);
        exist.setDateEntretien(statut.getDateEntretien());
        return statutRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Statut exist = findById(id);
        statutRepository.delete(exist);
    }
}
