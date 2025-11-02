package com.korbes.jarvis.inventory.processing.service.business.service;

import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesValidationException;

public interface InventoryProcessingService {
  void updateInventory(InventoryUpdateRequestDTO inventoryUpdateRequest, String action) throws KorbesValidationException;
}