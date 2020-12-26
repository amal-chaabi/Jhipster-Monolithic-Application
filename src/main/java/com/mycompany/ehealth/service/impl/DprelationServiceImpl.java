package com.mycompany.ehealth.service.impl;

import com.mycompany.ehealth.service.DprelationService;
import com.mycompany.ehealth.domain.Dprelation;
import com.mycompany.ehealth.repository.DprelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Dprelation}.
 */
@Service
@Transactional
public class DprelationServiceImpl implements DprelationService {

    private final Logger log = LoggerFactory.getLogger(DprelationServiceImpl.class);

    private final DprelationRepository dprelationRepository;

    public DprelationServiceImpl(DprelationRepository dprelationRepository) {
        this.dprelationRepository = dprelationRepository;
    }

    @Override
    public Dprelation save(Dprelation dprelation) {
        log.debug("Request to save Dprelation : {}", dprelation);
        return dprelationRepository.save(dprelation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dprelation> findAll() {
        log.debug("Request to get all Dprelations");
        return dprelationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Dprelation> findOne(Long id) {
        log.debug("Request to get Dprelation : {}", id);
        return dprelationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dprelation : {}", id);
        dprelationRepository.deleteById(id);
    }
}
