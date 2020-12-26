package com.mycompany.ehealth.service.impl;

import com.mycompany.ehealth.service.MedicalRepportService;
import com.mycompany.ehealth.domain.MedicalRepport;
import com.mycompany.ehealth.repository.MedicalRepportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalRepport}.
 */
@Service
@Transactional
public class MedicalRepportServiceImpl implements MedicalRepportService {

    private final Logger log = LoggerFactory.getLogger(MedicalRepportServiceImpl.class);

    private final MedicalRepportRepository medicalRepportRepository;

    public MedicalRepportServiceImpl(MedicalRepportRepository medicalRepportRepository) {
        this.medicalRepportRepository = medicalRepportRepository;
    }

    @Override
    public MedicalRepport save(MedicalRepport medicalRepport) {
        log.debug("Request to save MedicalRepport : {}", medicalRepport);
        return medicalRepportRepository.save(medicalRepport);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRepport> findAll() {
        log.debug("Request to get all MedicalRepports");
        return medicalRepportRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalRepport> findOne(Long id) {
        log.debug("Request to get MedicalRepport : {}", id);
        return medicalRepportRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalRepport : {}", id);
        medicalRepportRepository.deleteById(id);
    }
}
