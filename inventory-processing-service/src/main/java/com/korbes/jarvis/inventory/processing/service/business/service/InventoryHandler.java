package com.korbes.jarvis.inventory.processing.service.business.service;

import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;

public interface InventoryHandler {
  void manageInventory(InventoryUpdateRequestDTO inventoryUpdateRequest);
}