package com.mycompany.ehealth.repository;

import com.mycompany.ehealth.domain.MedicalRepport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalRepport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalRepportRepository extends JpaRepository<MedicalRepport, Long> {
}
