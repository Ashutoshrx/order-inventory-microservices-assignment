package com.korbes.jarvis.inventory.processing.service.business.factory.impl;

import com.korbes.jarvis.inventory.processing.service.business.service.InventoryHandler;
import com.korbes.jarvis.inventory.processing.service.data.repository.InventoryBatchRepository;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesNotFoundException;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.RESOURCE_DOES_NOT_EXIST;
import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
@Qualifier("inventoryUpdateHandler")
public class InventoryUpdateHandler implements InventoryHandler {
  private final InventoryBatchRepository inventoryBatchRepository;

  @Override
  public void manageInventory(InventoryUpdateRequestDTO inventoryUpdateRequest) {
    try {
      var inventoryBatch = inventoryBatchRepository.
              findByProductProductIdAndExpiryDateAfterAndQuantityAvailableGreaterThanEqualOrderByExpiryDate(
                      inventoryUpdateRequest.getProductId(), inventoryUpdateRequest.getQuantity());
      if (ObjectUtils.isEmpty(inventoryBatch)) {
        throw new KorbesNotFoundException(RESOURCE_DOES_NOT_EXIST);
      }
      inventoryBatch.setQuantityAvailable(inventoryBatch.getQuantityAvailable() - inventoryUpdateRequest.getQuantity());
      inventoryBatchRepository.save(inventoryBatch);
    } catch (KorbesNotFoundException e) {
      throw e;
    } catch (Exception e) {
      throw new KorbesServiceRunTimeException(SYSTEM_ERROR + e.getMessage(), e);
    }
  }
}