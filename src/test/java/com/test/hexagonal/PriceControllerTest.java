package com.test.hexagonal;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriceRequestAt10On14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    void testGetPriceRequestAt16On14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    @Test
    void testGetPriceRequestAt21On14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    void testGetPriceRequestAt10On15th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    @Test
    void testGetPriceRequestAt21On16th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }

    @Test
    void testGetPriceNotFound() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "7")
                        .param("productId", "35455")
                        .param("date", "2020-06-16T21:00:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPriceBadRequest() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "test")
                        .param("productId", "35455")
                        .param("date", "2020-06-16T21:00:00"))
                .andExpect(status().isBadRequest());
    }
}
