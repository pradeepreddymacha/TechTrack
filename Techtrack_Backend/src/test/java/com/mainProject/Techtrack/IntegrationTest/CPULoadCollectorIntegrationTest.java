package com.mainProject.Techtrack.IntegrationTest;

import com.mainProject.Techtrack.CPULoadCollector;
import com.mainProject.userVariables.CPUVaraiable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CPULoadCollector.class)
@AutoConfigureMockMvc
public class CPULoadCollectorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testInsertMetricsEndpoint() throws Exception {
        // Arrange
        CPUVaraiable metrics = new CPUVaraiable(System.currentTimeMillis(), 75); // Assuming load percentage is 75

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/cpu_metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"timestamp\":" + metrics.getTimestamp() + ", \"loadPercentage\":" + metrics.getLoadPercentage() + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Metrics inserted successfully!"));
    }
}
