package com.mycompany.ehealth.web.rest;

import com.mycompany.ehealth.domain.Dprelation;
import com.mycompany.ehealth.service.DprelationService;
import com.mycompany.ehealth.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.ehealth.domain.Dprelation}.
 */
@RestController
@RequestMapping("/api")
public class DprelationResource {

    private final Logger log = LoggerFactory.getLogger(DprelationResource.class);

    private static final String ENTITY_NAME = "dprelation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DprelationService dprelationService;

    public DprelationResource(DprelationService dprelationService) {
        this.dprelationService = dprelationService;
    }

    /**
     * {@code POST  /dprelations} : Create a new dprelation.
     *
     * @param dprelation the dprelation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dprelation, or with status {@code 400 (Bad Request)} if the dprelation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dprelations")
    public ResponseEntity<Dprelation> createDprelation(@RequestBody Dprelation dprelation) throws URISyntaxException {
        log.debug("REST request to save Dprelation : {}", dprelation);
        if (dprelation.getId() != null) {
            throw new BadRequestAlertException("A new dprelation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dprelation result = dprelationService.save(dprelation);
        return ResponseEntity.created(new URI("/api/dprelations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dprelations} : Updates an existing dprelation.
     *
     * @param dprelation the dprelation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dprelation,
     * or with status {@code 400 (Bad Request)} if the dprelation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dprelation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dprelations")
    public ResponseEntity<Dprelation> updateDprelation(@RequestBody Dprelation dprelation) throws URISyntaxException {
        log.debug("REST request to update Dprelation : {}", dprelation);
        if (dprelation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dprelation result = dprelationService.save(dprelation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dprelation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dprelations} : get all the dprelations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dprelations in body.
     */
    @GetMapping("/dprelations")
    public List<Dprelation> getAllDprelations() {
        log.debug("REST request to get all Dprelations");
        return dprelationService.findAll();
    }

    /**
     * {@code GET  /dprelations/:id} : get the "id" dprelation.
     *
     * @param id the id of the dprelation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dprelation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dprelations/{id}")
    public ResponseEntity<Dprelation> getDprelation(@PathVariable Long id) {
        log.debug("REST request to get Dprelation : {}", id);
        Optional<Dprelation> dprelation = dprelationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dprelation);
    }

    /**
     * {@code DELETE  /dprelations/:id} : delete the "id" dprelation.
     *
     * @param id the id of the dprelation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dprelations/{id}")
    public ResponseEntity<Void> deleteDprelation(@PathVariable Long id) {
        log.debug("REST request to delete Dprelation : {}", id);
        dprelationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
