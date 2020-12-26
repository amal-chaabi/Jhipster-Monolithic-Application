package com.mycompany.ehealth.web.rest;

import com.mycompany.ehealth.domain.MedicalRepport;
import com.mycompany.ehealth.service.MedicalRepportService;
import com.mycompany.ehealth.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.ehealth.domain.MedicalRepport}.
 */
@RestController
@RequestMapping("/api")
public class MedicalRepportResource {

    private final Logger log = LoggerFactory.getLogger(MedicalRepportResource.class);

    private static final String ENTITY_NAME = "medicalRepport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalRepportService medicalRepportService;

    public MedicalRepportResource(MedicalRepportService medicalRepportService) {
        this.medicalRepportService = medicalRepportService;
    }

    /**
     * {@code POST  /medical-repports} : Create a new medicalRepport.
     *
     * @param medicalRepport the medicalRepport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalRepport, or with status {@code 400 (Bad Request)} if the medicalRepport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-repports")
    public ResponseEntity<MedicalRepport> createMedicalRepport(@Valid @RequestBody MedicalRepport medicalRepport) throws URISyntaxException {
        log.debug("REST request to save MedicalRepport : {}", medicalRepport);
        if (medicalRepport.getId() != null) {
            throw new BadRequestAlertException("A new medicalRepport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalRepport result = medicalRepportService.save(medicalRepport);
        return ResponseEntity.created(new URI("/api/medical-repports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-repports} : Updates an existing medicalRepport.
     *
     * @param medicalRepport the medicalRepport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalRepport,
     * or with status {@code 400 (Bad Request)} if the medicalRepport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalRepport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-repports")
    public ResponseEntity<MedicalRepport> updateMedicalRepport(@Valid @RequestBody MedicalRepport medicalRepport) throws URISyntaxException {
        log.debug("REST request to update MedicalRepport : {}", medicalRepport);
        if (medicalRepport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalRepport result = medicalRepportService.save(medicalRepport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medicalRepport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-repports} : get all the medicalRepports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalRepports in body.
     */
    @GetMapping("/medical-repports")
    public List<MedicalRepport> getAllMedicalRepports() {
        log.debug("REST request to get all MedicalRepports");
        return medicalRepportService.findAll();
    }

    /**
     * {@code GET  /medical-repports/:id} : get the "id" medicalRepport.
     *
     * @param id the id of the medicalRepport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalRepport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-repports/{id}")
    public ResponseEntity<MedicalRepport> getMedicalRepport(@PathVariable Long id) {
        log.debug("REST request to get MedicalRepport : {}", id);
        Optional<MedicalRepport> medicalRepport = medicalRepportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalRepport);
    }

    /**
     * {@code DELETE  /medical-repports/:id} : delete the "id" medicalRepport.
     *
     * @param id the id of the medicalRepport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-repports/{id}")
    public ResponseEntity<Void> deleteMedicalRepport(@PathVariable Long id) {
        log.debug("REST request to delete MedicalRepport : {}", id);
        medicalRepportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
