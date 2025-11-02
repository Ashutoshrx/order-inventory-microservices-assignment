package com.korbes.jarvis.inventory.processing.service.rest.controller;

import com.korbes.jarvis.inventory.processing.service.business.service.InventoryProcessingService;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesValidationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InventoryProcessingController {
  private final InventoryProcessingService inventoryProcessingService;

  @PostMapping("/v1/inventory")
  public void updateInventory(@RequestBody @Valid InventoryUpdateRequestDTO inventoryUpdateRequest,
                              @RequestParam String action)
          throws KorbesValidationException {
    inventoryProcessingService.updateInventory(inventoryUpdateRequest, action);
  }
}