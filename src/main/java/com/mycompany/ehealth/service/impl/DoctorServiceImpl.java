package com.mycompany.ehealth.service.impl;

import com.mycompany.ehealth.service.DoctorService;
import com.mycompany.ehealth.domain.Doctor;
import com.mycompany.ehealth.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Doctor}.
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        log.debug("Request to save Doctor : {}", doctor);
        return doctorRepository.save(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        log.debug("Request to get all Doctors");
        return doctorRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findOne(Long id) {
        log.debug("Request to get Doctor : {}", id);
        return doctorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Doctor : {}", id);
        doctorRepository.deleteById(id);
    }
}
