package com.mycompany.ehealth.service;

import com.mycompany.ehealth.domain.Dprelation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Dprelation}.
 */
public interface DprelationService {

    /**
     * Save a dprelation.
     *
     * @param dprelation the entity to save.
     * @return the persisted entity.
     */
    Dprelation save(Dprelation dprelation);

    /**
     * Get all the dprelations.
     *
     * @return the list of entities.
     */
    List<Dprelation> findAll();


    /**
     * Get the "id" dprelation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Dprelation> findOne(Long id);

    /**
     * Delete the "id" dprelation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
