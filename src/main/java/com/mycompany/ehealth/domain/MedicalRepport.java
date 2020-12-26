package com.mycompany.ehealth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MedicalRepport.
 */
@Entity
@Table(name = "medical_repport")
public class MedicalRepport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type_of_repport", nullable = false)
    private String typeOfRepport;

    @NotNull
    @Column(name = "result", nullable = false)
    private String result;

    @ManyToOne
    @JsonIgnoreProperties(value = "medicalRepports", allowSetters = true)
    private Patient medicalRepportPatient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfRepport() {
        return typeOfRepport;
    }

    public MedicalRepport typeOfRepport(String typeOfRepport) {
        this.typeOfRepport = typeOfRepport;
        return this;
    }

    public void setTypeOfRepport(String typeOfRepport) {
        this.typeOfRepport = typeOfRepport;
    }

    public String getResult() {
        return result;
    }

    public MedicalRepport result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Patient getMedicalRepportPatient() {
        return medicalRepportPatient;
    }

    public MedicalRepport medicalRepportPatient(Patient patient) {
        this.medicalRepportPatient = patient;
        return this;
    }

    public void setMedicalRepportPatient(Patient patient) {
        this.medicalRepportPatient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalRepport)) {
            return false;
        }
        return id != null && id.equals(((MedicalRepport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalRepport{" +
            "id=" + getId() +
            ", typeOfRepport='" + getTypeOfRepport() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
