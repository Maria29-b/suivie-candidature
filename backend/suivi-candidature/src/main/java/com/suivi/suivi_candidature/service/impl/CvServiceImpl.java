package com.suivi.suivi_candidature.service.impl;

import com.suivi.suivi_candidature.entity.Cv;
import com.suivi.suivi_candidature.exception.ResourceNotFoundException;
import com.suivi.suivi_candidature.repository.CvRepository;
import com.suivi.suivi_candidature.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    private final CvRepository cvRepository;

    @Override
    public List<Cv> findAll() {
        return cvRepository.findAll();
    }

    @Override
    public Cv findById(String id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV introuvable: " + id));
    }

    @Override
    public Cv create(Cv cv) {
        return cvRepository.save(cv);
    }

    @Override
    public Cv update(String id, Cv cv) {
        Cv exist = findById(id);
        exist.setDateCreation(cv.getDateCreation());
        exist.setCandidat(cv.getCandidat());
        return cvRepository.save(exist);
    }

    @Override
    public void delete(String id) {
        Cv exist = findById(id);
        cvRepository.delete(exist);
    }
}
