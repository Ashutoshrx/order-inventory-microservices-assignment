package com.korbes.jarvis.inventory.processing.service.rest.controller;

import com.korbes.jarvis.inventory.processing.service.business.service.InventoryProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = InventoryProcessingController.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryProcessingControllerTest {
  @MockitoBean
  private InventoryProcessingService inventoryProcessingService;
  @InjectMocks
  private InventoryProcessingController inventoryProcessingController;
  @Autowired
  private MockMvc mockMvc;
  private String updateInventoryUri;
  private String  requestBody;

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:8080";
    updateInventoryUri = baseUrl.concat("/v1/inventory");
     requestBody = """
            {
                "productId": 3,
                "quantity": 5
            }
            """;
  }

  @Test
  void updateInventory() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(updateInventoryUri).
            contentType(MediaType.APPLICATION_JSON).content(requestBody).param("action", "UPDATE_STOCK")).andExpect(status().is2xxSuccessful());
  }

  @Test
  void updateInventory_throwsException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(updateInventoryUri).
            contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().is4xxClientError());
  }
}