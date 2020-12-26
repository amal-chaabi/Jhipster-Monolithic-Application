package com.mycompany.ehealth.web.rest;

import com.mycompany.ehealth.EHealthApp;
import com.mycompany.ehealth.domain.MedicalRepport;
import com.mycompany.ehealth.repository.MedicalRepportRepository;
import com.mycompany.ehealth.service.MedicalRepportService;

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
 * Integration tests for the {@link MedicalRepportResource} REST controller.
 */
@SpringBootTest(classes = EHealthApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalRepportResourceIT {

    private static final String DEFAULT_TYPE_OF_REPPORT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_REPPORT = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    @Autowired
    private MedicalRepportRepository medicalRepportRepository;

    @Autowired
    private MedicalRepportService medicalRepportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalRepportMockMvc;

    private MedicalRepport medicalRepport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalRepport createEntity(EntityManager em) {
        MedicalRepport medicalRepport = new MedicalRepport()
            .typeOfRepport(DEFAULT_TYPE_OF_REPPORT)
            .result(DEFAULT_RESULT);
        return medicalRepport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalRepport createUpdatedEntity(EntityManager em) {
        MedicalRepport medicalRepport = new MedicalRepport()
            .typeOfRepport(UPDATED_TYPE_OF_REPPORT)
            .result(UPDATED_RESULT);
        return medicalRepport;
    }

    @BeforeEach
    public void initTest() {
        medicalRepport = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalRepport() throws Exception {
        int databaseSizeBeforeCreate = medicalRepportRepository.findAll().size();
        // Create the MedicalRepport
        restMedicalRepportMockMvc.perform(post("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalRepport)))
            .andExpect(status().isCreated());

        // Validate the MedicalRepport in the database
        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalRepport testMedicalRepport = medicalRepportList.get(medicalRepportList.size() - 1);
        assertThat(testMedicalRepport.getTypeOfRepport()).isEqualTo(DEFAULT_TYPE_OF_REPPORT);
        assertThat(testMedicalRepport.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    @Transactional
    public void createMedicalRepportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalRepportRepository.findAll().size();

        // Create the MedicalRepport with an existing ID
        medicalRepport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalRepportMockMvc.perform(post("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalRepport)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalRepport in the database
        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeOfRepportIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalRepportRepository.findAll().size();
        // set the field null
        medicalRepport.setTypeOfRepport(null);

        // Create the MedicalRepport, which fails.


        restMedicalRepportMockMvc.perform(post("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalRepport)))
            .andExpect(status().isBadRequest());

        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalRepportRepository.findAll().size();
        // set the field null
        medicalRepport.setResult(null);

        // Create the MedicalRepport, which fails.


        restMedicalRepportMockMvc.perform(post("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalRepport)))
            .andExpect(status().isBadRequest());

        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicalRepports() throws Exception {
        // Initialize the database
        medicalRepportRepository.saveAndFlush(medicalRepport);

        // Get all the medicalRepportList
        restMedicalRepportMockMvc.perform(get("/api/medical-repports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalRepport.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeOfRepport").value(hasItem(DEFAULT_TYPE_OF_REPPORT)))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)));
    }
    
    @Test
    @Transactional
    public void getMedicalRepport() throws Exception {
        // Initialize the database
        medicalRepportRepository.saveAndFlush(medicalRepport);

        // Get the medicalRepport
        restMedicalRepportMockMvc.perform(get("/api/medical-repports/{id}", medicalRepport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalRepport.getId().intValue()))
            .andExpect(jsonPath("$.typeOfRepport").value(DEFAULT_TYPE_OF_REPPORT))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalRepport() throws Exception {
        // Get the medicalRepport
        restMedicalRepportMockMvc.perform(get("/api/medical-repports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalRepport() throws Exception {
        // Initialize the database
        medicalRepportService.save(medicalRepport);

        int databaseSizeBeforeUpdate = medicalRepportRepository.findAll().size();

        // Update the medicalRepport
        MedicalRepport updatedMedicalRepport = medicalRepportRepository.findById(medicalRepport.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalRepport are not directly saved in db
        em.detach(updatedMedicalRepport);
        updatedMedicalRepport
            .typeOfRepport(UPDATED_TYPE_OF_REPPORT)
            .result(UPDATED_RESULT);

        restMedicalRepportMockMvc.perform(put("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMedicalRepport)))
            .andExpect(status().isOk());

        // Validate the MedicalRepport in the database
        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeUpdate);
        MedicalRepport testMedicalRepport = medicalRepportList.get(medicalRepportList.size() - 1);
        assertThat(testMedicalRepport.getTypeOfRepport()).isEqualTo(UPDATED_TYPE_OF_REPPORT);
        assertThat(testMedicalRepport.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalRepport() throws Exception {
        int databaseSizeBeforeUpdate = medicalRepportRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalRepportMockMvc.perform(put("/api/medical-repports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalRepport)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalRepport in the database
        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalRepport() throws Exception {
        // Initialize the database
        medicalRepportService.save(medicalRepport);

        int databaseSizeBeforeDelete = medicalRepportRepository.findAll().size();

        // Delete the medicalRepport
        restMedicalRepportMockMvc.perform(delete("/api/medical-repports/{id}", medicalRepport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalRepport> medicalRepportList = medicalRepportRepository.findAll();
        assertThat(medicalRepportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
