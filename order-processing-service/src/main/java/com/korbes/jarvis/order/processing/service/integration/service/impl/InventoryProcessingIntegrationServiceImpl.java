package com.korbes.jarvis.order.processing.service.integration.service.impl;

import com.korbes.jarvis.order.processing.service.integration.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.order.processing.service.integration.service.InventoryProcessingIntegrationService;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceIntegrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.korbes.jarvis.order.processing.service.lib.error.ErrorMessage.INTEGRATION_FAILURE;

@Service
@RequiredArgsConstructor
public class InventoryProcessingIntegrationServiceImpl implements InventoryProcessingIntegrationService {
  private final WebClient inventoryProcessingWebClient;
  @Value("${inventory.service.update.stock.uri}")
  private String updateStockUri;

  @Override
  public void updateInventory(InventoryUpdateRequestDTO inventoryUpdateRequest, String action) throws KorbesServiceIntegrationException {
    try {
      inventoryProcessingWebClient.post().uri(uriBuilder -> uriBuilder.path(updateStockUri).queryParam("action",
              action).build()).bodyValue(inventoryUpdateRequest).retrieve().bodyToMono(Void.class).block();
    } catch (Exception e) {
      throw new KorbesServiceIntegrationException(INTEGRATION_FAILURE + e.getMessage(), e);
    }
  }
}
