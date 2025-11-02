package com.korbes.jarvis.inventory.processing.service.business.service.impl;

import com.korbes.jarvis.inventory.processing.service.business.factory.InventoryActionHandler;
import com.korbes.jarvis.inventory.processing.service.business.service.InventoryHandler;
import com.korbes.jarvis.inventory.processing.service.business.service.InventoryProcessingService;
import com.korbes.jarvis.inventory.processing.service.business.validator.ActionValidator;
import com.korbes.jarvis.inventory.processing.service.data.repository.InventoryBatchRepository;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesNotFoundException;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.RESOURCE_DOES_NOT_EXIST;
import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
public class InventoryProcessingServiceImpl implements InventoryProcessingService {
  private final InventoryActionHandler inventoryActionHandler;

  @Override
  public void updateInventory(InventoryUpdateRequestDTO inventoryUpdateRequest, String action) throws KorbesValidationException {
    ActionValidator.validate(action);
    System.out.println("Started updating inventory with action: " + action);
    try {
      InventoryHandler inventoryHandler = inventoryActionHandler.fetchHandler(action);
      inventoryHandler.manageInventory(inventoryUpdateRequest);
      System.out.println("Successfully updated the inventoryBatch");
    } catch (KorbesNotFoundException e) {
      throw e;
    } catch (Exception e) {
      throw new KorbesServiceRunTimeException(SYSTEM_ERROR + e.getMessage(), e);
    }
  }
}