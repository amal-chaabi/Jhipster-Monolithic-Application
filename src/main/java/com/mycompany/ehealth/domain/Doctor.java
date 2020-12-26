package com.mycompany.ehealth.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Doctor.
 */
@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "lastname", length = 20, nullable = false)
    private String lastname;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "firstname", length = 20, nullable = false)
    private String firstname;

    @Column(name = "title")
    private String title;

    @Column(name = "experience")
    private String experience;

    @Min(value = 20)
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "type_of_speciality", nullable = false)
    private String typeOfSpeciality;

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

    public Doctor lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Doctor firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTitle() {
        return title;
    }

    public Doctor title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperience() {
        return experience;
    }

    public Doctor experience(String experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getAge() {
        return age;
    }

    public Doctor age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTypeOfSpeciality() {
        return typeOfSpeciality;
    }

    public Doctor typeOfSpeciality(String typeOfSpeciality) {
        this.typeOfSpeciality = typeOfSpeciality;
        return this;
    }

    public void setTypeOfSpeciality(String typeOfSpeciality) {
        this.typeOfSpeciality = typeOfSpeciality;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doctor)) {
            return false;
        }
        return id != null && id.equals(((Doctor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Doctor{" +
            "id=" + getId() +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", title='" + getTitle() + "'" +
            ", experience='" + getExperience() + "'" +
            ", age=" + getAge() +
            ", typeOfSpeciality='" + getTypeOfSpeciality() + "'" +
            "}";
    }
}
