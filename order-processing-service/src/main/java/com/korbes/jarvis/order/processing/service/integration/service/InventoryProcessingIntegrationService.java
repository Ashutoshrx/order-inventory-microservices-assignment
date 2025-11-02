package com.korbes.jarvis.order.processing.service.integration.service;

import com.korbes.jarvis.order.processing.service.integration.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceIntegrationException;

public interface InventoryProcessingIntegrationService {
  void updateInventory(InventoryUpdateRequestDTO inventoryUpdateRequest, String action) throws KorbesServiceIntegrationException;
}
