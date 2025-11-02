package com.korbes.jarvis.inventory.processing.service.business.service;

import com.korbes.jarvis.inventory.processing.service.dto.InventoryBatchDTO;

import java.util.List;

public interface InventoryViewerService {

  List<InventoryBatchDTO> fetchInventoryBatches(Long productId);
}
