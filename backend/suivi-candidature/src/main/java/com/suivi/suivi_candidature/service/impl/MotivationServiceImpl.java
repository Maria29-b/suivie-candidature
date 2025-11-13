package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Motivation;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.MotivationRepository;
import com.suivi.suivi_candidature.service.MotivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotivationServiceImpl implements MotivationService {

    private final MotivationRepository motivationRepository;

    @Override
    public List<Motivation> findAll() {
        return motivationRepository.findAll();
    }

    @Override
    public Motivation findById(String id) {
        return motivationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motivation introuvable: " + id));
    }

    @Override
    public Motivation create(Motivation motivation) {
        return motivationRepository.save(motivation);
    }

    @Override
    public Motivation update(String id, Motivation motivation) {
        Motivation exist = findById(id);
        exist.setDateCreation(motivation.getDateCreation());
        exist.setCandidat(motivation.getCandidat());
        return motivationRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Motivation exist = findById(id);
        motivationRepository.delete(exist);
    }
}
