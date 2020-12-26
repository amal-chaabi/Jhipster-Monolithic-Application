package com.mycompany.ehealth.repository;

import com.mycompany.ehealth.domain.Dprelation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dprelation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DprelationRepository extends JpaRepository<Dprelation, Long> {
}
