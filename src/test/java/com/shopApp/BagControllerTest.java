package com.shopApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopApp.DTO.response.BagDTO;
import com.shopApp.controller.BagController;
import com.shopApp.service.BagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BagControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BagService bagService;

    @InjectMocks
    private BagController bagController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bagController).build();
    }

    @Test
    public void testGetAllBags() throws Exception {
        List<BagDTO> bagDTOs = new ArrayList<>();
        bagDTOs.add(new BagDTO(1L, "Brand1", 100.0, "China", "Leather"));
        bagDTOs.add(new BagDTO(2L, "Brand2", 150.0, "Italy", "Canvas"));

        when(bagService.getAllBagDTOs()).thenReturn(bagDTOs);

        mockMvc.perform(get("/bags")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].brand").value("Brand1"))
                .andExpect(jsonPath("$[0].price").value(100.0))
                .andExpect(jsonPath("$[0].production").value("China"))
                .andExpect(jsonPath("$[0].material").value("Leather"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].brand").value("Brand2"))
                .andExpect(jsonPath("$[1].price").value(150.0))
                .andExpect(jsonPath("$[1].production").value("Italy"))
                .andExpect(jsonPath("$[1].material").value("Canvas"));
    }

}
