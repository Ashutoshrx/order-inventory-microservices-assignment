package com.korbes.jarvis.order.processing.service.rest.controller;

import com.korbes.jarvis.order.processing.service.business.service.OrderProcessingService;
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
@ContextConfiguration(classes = OrderProcessingController.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderProcessingControllerTest {
  @MockitoBean
  private OrderProcessingService orderProcessingService;
  @InjectMocks
  private OrderProcessingController orderProcessingController;
  @Autowired
  private MockMvc mockMvc;
  private String placeOrderUri;

  @BeforeEach
  void setUp() {
    String baseUrl = "http://localhost:8080";
    placeOrderUri = baseUrl.concat("/v1/order");
  }

  @Test
  void placeOrder() throws Exception {
    String requestBody = """
            {
                "productId": 3,
                "quantity": 5
            }
            """;
    mockMvc.perform(MockMvcRequestBuilders.post(placeOrderUri).
            contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().is2xxSuccessful());
  }

  @Test
  void placeOrder_throwsException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(placeOrderUri + "invalidPath").
            contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
  }
}