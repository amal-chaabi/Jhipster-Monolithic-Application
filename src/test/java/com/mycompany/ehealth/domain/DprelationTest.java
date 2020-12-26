package com.mycompany.ehealth.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.ehealth.web.rest.TestUtil;

public class DprelationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dprelation.class);
        Dprelation dprelation1 = new Dprelation();
        dprelation1.setId(1L);
        Dprelation dprelation2 = new Dprelation();
        dprelation2.setId(dprelation1.getId());
        assertThat(dprelation1).isEqualTo(dprelation2);
        dprelation2.setId(2L);
        assertThat(dprelation1).isNotEqualTo(dprelation2);
        dprelation1.setId(null);
        assertThat(dprelation1).isNotEqualTo(dprelation2);
    }
}
