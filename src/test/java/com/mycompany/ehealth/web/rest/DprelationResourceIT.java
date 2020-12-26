package com.mycompany.ehealth.web.rest;

import com.mycompany.ehealth.EHealthApp;
import com.mycompany.ehealth.domain.Dprelation;
import com.mycompany.ehealth.repository.DprelationRepository;
import com.mycompany.ehealth.service.DprelationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DprelationResource} REST controller.
 */
@SpringBootTest(classes = EHealthApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DprelationResourceIT {

    @Autowired
    private DprelationRepository dprelationRepository;

    @Autowired
    private DprelationService dprelationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDprelationMockMvc;

    private Dprelation dprelation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dprelation createEntity(EntityManager em) {
        Dprelation dprelation = new Dprelation();
        return dprelation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dprelation createUpdatedEntity(EntityManager em) {
        Dprelation dprelation = new Dprelation();
        return dprelation;
    }

    @BeforeEach
    public void initTest() {
        dprelation = createEntity(em);
    }

    @Test
    @Transactional
    public void createDprelation() throws Exception {
        int databaseSizeBeforeCreate = dprelationRepository.findAll().size();
        // Create the Dprelation
        restDprelationMockMvc.perform(post("/api/dprelations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dprelation)))
            .andExpect(status().isCreated());

        // Validate the Dprelation in the database
        List<Dprelation> dprelationList = dprelationRepository.findAll();
        assertThat(dprelationList).hasSize(databaseSizeBeforeCreate + 1);
        Dprelation testDprelation = dprelationList.get(dprelationList.size() - 1);
    }

    @Test
    @Transactional
    public void createDprelationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dprelationRepository.findAll().size();

        // Create the Dprelation with an existing ID
        dprelation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDprelationMockMvc.perform(post("/api/dprelations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dprelation)))
            .andExpect(status().isBadRequest());

        // Validate the Dprelation in the database
        List<Dprelation> dprelationList = dprelationRepository.findAll();
        assertThat(dprelationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDprelations() throws Exception {
        // Initialize the database
        dprelationRepository.saveAndFlush(dprelation);

        // Get all the dprelationList
        restDprelationMockMvc.perform(get("/api/dprelations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dprelation.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getDprelation() throws Exception {
        // Initialize the database
        dprelationRepository.saveAndFlush(dprelation);

        // Get the dprelation
        restDprelationMockMvc.perform(get("/api/dprelations/{id}", dprelation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dprelation.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDprelation() throws Exception {
        // Get the dprelation
        restDprelationMockMvc.perform(get("/api/dprelations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDprelation() throws Exception {
        // Initialize the database
        dprelationService.save(dprelation);

        int databaseSizeBeforeUpdate = dprelationRepository.findAll().size();

        // Update the dprelation
        Dprelation updatedDprelation = dprelationRepository.findById(dprelation.getId()).get();
        // Disconnect from session so that the updates on updatedDprelation are not directly saved in db
        em.detach(updatedDprelation);

        restDprelationMockMvc.perform(put("/api/dprelations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDprelation)))
            .andExpect(status().isOk());

        // Validate the Dprelation in the database
        List<Dprelation> dprelationList = dprelationRepository.findAll();
        assertThat(dprelationList).hasSize(databaseSizeBeforeUpdate);
        Dprelation testDprelation = dprelationList.get(dprelationList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingDprelation() throws Exception {
        int databaseSizeBeforeUpdate = dprelationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDprelationMockMvc.perform(put("/api/dprelations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dprelation)))
            .andExpect(status().isBadRequest());

        // Validate the Dprelation in the database
        List<Dprelation> dprelationList = dprelationRepository.findAll();
        assertThat(dprelationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDprelation() throws Exception {
        // Initialize the database
        dprelationService.save(dprelation);

        int databaseSizeBeforeDelete = dprelationRepository.findAll().size();

        // Delete the dprelation
        restDprelationMockMvc.perform(delete("/api/dprelations/{id}", dprelation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dprelation> dprelationList = dprelationRepository.findAll();
        assertThat(dprelationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
