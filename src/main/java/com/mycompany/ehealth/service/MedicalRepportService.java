package com.mycompany.ehealth.service;

import com.mycompany.ehealth.domain.MedicalRepport;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MedicalRepport}.
 */
public interface MedicalRepportService {

    /**
     * Save a medicalRepport.
     *
     * @param medicalRepport the entity to save.
     * @return the persisted entity.
     */
    MedicalRepport save(MedicalRepport medicalRepport);

    /**
     * Get all the medicalRepports.
     *
     * @return the list of entities.
     */
    List<MedicalRepport> findAll();


    /**
     * Get the "id" medicalRepport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalRepport> findOne(Long id);

    /**
     * Delete the "id" medicalRepport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
