package com.mycompany.ehealth.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    @Column(name = "lastname", length = 25, nullable = false)
    private String lastname;

    @NotNull
    @Size(min = 3, max = 25)
    @Column(name = "firstname", length = 25, nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "confidentiality_agreement", nullable = false)
    private String confidentialityAgreement;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Column(name = "hometown", nullable = false)
    private String hometown;

    @NotNull
    @Column(name = "adress", nullable = false)
    private String adress;

    @NotNull
    @Column(name = "social_security_number", nullable = false)
    private String socialSecurityNumber;

    @NotNull
    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;

    @OneToMany(mappedBy = "medicalRepportPatient")
    private Set<MedicalRepport> patientMedicalrepports = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public Patient lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Patient firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getConfidentialityAgreement() {
        return confidentialityAgreement;
    }

    public Patient confidentialityAgreement(String confidentialityAgreement) {
        this.confidentialityAgreement = confidentialityAgreement;
        return this;
    }

    public void setConfidentialityAgreement(String confidentialityAgreement) {
        this.confidentialityAgreement = confidentialityAgreement;
    }

    public Integer getAge() {
        return age;
    }

    public Patient age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public Patient hometown(String hometown) {
        this.hometown = hometown;
        return this;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getAdress() {
        return adress;
    }

    public Patient adress(String adress) {
        this.adress = adress;
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public Patient socialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Patient phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Set<MedicalRepport> getPatientMedicalrepports() {
        return patientMedicalrepports;
    }

    public Patient patientMedicalrepports(Set<MedicalRepport> medicalRepports) {
        this.patientMedicalrepports = medicalRepports;
        return this;
    }

    public Patient addPatientMedicalrepport(MedicalRepport medicalRepport) {
        this.patientMedicalrepports.add(medicalRepport);
        medicalRepport.setMedicalRepportPatient(this);
        return this;
    }

    public Patient removePatientMedicalrepport(MedicalRepport medicalRepport) {
        this.patientMedicalrepports.remove(medicalRepport);
        medicalRepport.setMedicalRepportPatient(null);
        return this;
    }

    public void setPatientMedicalrepports(Set<MedicalRepport> medicalRepports) {
        this.patientMedicalrepports = medicalRepports;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", confidentialityAgreement='" + getConfidentialityAgreement() + "'" +
            ", age=" + getAge() +
            ", hometown='" + getHometown() + "'" +
            ", adress='" + getAdress() + "'" +
            ", socialSecurityNumber='" + getSocialSecurityNumber() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            "}";
    }
}
