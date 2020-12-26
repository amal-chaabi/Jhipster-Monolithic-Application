package com.mycompany.ehealth.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.ehealth.web.rest.TestUtil;

public class MedicalRepportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalRepport.class);
        MedicalRepport medicalRepport1 = new MedicalRepport();
        medicalRepport1.setId(1L);
        MedicalRepport medicalRepport2 = new MedicalRepport();
        medicalRepport2.setId(medicalRepport1.getId());
        assertThat(medicalRepport1).isEqualTo(medicalRepport2);
        medicalRepport2.setId(2L);
        assertThat(medicalRepport1).isNotEqualTo(medicalRepport2);
        medicalRepport1.setId(null);
        assertThat(medicalRepport1).isNotEqualTo(medicalRepport2);
    }
}
