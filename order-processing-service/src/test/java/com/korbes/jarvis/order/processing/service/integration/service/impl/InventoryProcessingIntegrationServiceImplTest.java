package com.korbes.jarvis.order.processing.service.integration.service.impl;

import com.korbes.jarvis.order.processing.service.business.enums.ActionType;
import com.korbes.jarvis.order.processing.service.integration.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceIntegrationException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static com.korbes.jarvis.order.processing.service.lib.error.ErrorMessage.INTEGRATION_FAILURE;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryProcessingIntegrationServiceImplTest {
  private MockWebServer mockWebServer;
  @InjectMocks
  private InventoryProcessingIntegrationServiceImpl inventoryProcessingIntegrationService;
  private InventoryUpdateRequestDTO inventoryUpdateRequest;

  @BeforeEach
  void setUp() throws IOException {
    inventoryUpdateRequest =
            InventoryUpdateRequestDTO.builder().quantity(1).productId(1L).build();
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    WebClient webClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build();
    inventoryProcessingIntegrationService = new InventoryProcessingIntegrationServiceImpl(webClient);
    ReflectionTestUtils.setField(inventoryProcessingIntegrationService, "updateStockUri", "/v1/inventory");
  }

  @AfterEach
  void tearDown() throws IOException {
    mockWebServer.shutdown();
  }


  @Test
  void updateInventory() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(""));
    Assertions.assertDoesNotThrow(() -> inventoryProcessingIntegrationService.
            updateInventory(inventoryUpdateRequest, ActionType.UPDATE_STOCK.getValue()));
  }

  @Test
  void updateInventory_throwsException() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody("Internal Server Error Occurred"));
    KorbesServiceIntegrationException exception = Assertions.assertThrows(KorbesServiceIntegrationException.class, () -> inventoryProcessingIntegrationService.
            updateInventory(inventoryUpdateRequest, ActionType.UPDATE_STOCK.getValue()));
    Assertions.assertTrue(exception.getMessage().contains(INTEGRATION_FAILURE));
  }
}