package com.mainProject.Techtrack.IntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemMetricsCollectorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInsertMetricsEndpoint() throws Exception {
        String jsonContent = "{\"timestamp\":" + System.currentTimeMillis() + ", \"osName\":\"Windows 10\", \"osVersion\":\"10.0\", \"hostName\":\"Host123\", \"macAddress\":\"00-14-22-01-23-45\", \"systemManufacturer\":\"Dell\", \"systemModel\":\"XPS\", \"systemType\":\"Desktop\", \"totalPhysicalMemory\":16384}";

        mockMvc.perform(post("/metrics_new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().string("Metrics inserted successfully!"));
    }
}

