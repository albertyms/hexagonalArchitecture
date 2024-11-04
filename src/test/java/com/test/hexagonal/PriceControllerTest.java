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
    public void testGetPriceCase1() throws Exception {
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
    public void testGetPriceCase2() throws Exception {
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
    public void testGetPriceCase3() throws Exception {
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
    public void testGetPriceCase4() throws Exception {
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
    public void testGetPriceCase5() throws Exception {
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
    public void testGetPriceCase6() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("brandId", "7")
                        .param("productId", "35455")
                        .param("date", "2020-06-16T21:00:00"))
                .andExpect(status().isNotFound());
    }
}
