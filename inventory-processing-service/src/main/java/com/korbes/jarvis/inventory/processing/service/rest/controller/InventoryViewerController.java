package com.korbes.jarvis.inventory.processing.service.rest.controller;

import com.korbes.jarvis.inventory.processing.service.business.service.InventoryViewerService;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryBatchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InventoryViewerController {
  private final InventoryViewerService inventoryViewerService;

  @GetMapping("/v1/inventory-batches/{productId}")
  public List<InventoryBatchDTO> fetchInventoryBatches(@PathVariable Long productId) {
    return inventoryViewerService.fetchInventoryBatches(productId);
  }
}