package com.mycompany.ehealth.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Dprelation.
 */
@Entity
@Table(name = "dprelation")
public class Dprelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dprelation)) {
            return false;
        }
        return id != null && id.equals(((Dprelation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dprelation{" +
            "id=" + getId() +
            "}";
    }
}
