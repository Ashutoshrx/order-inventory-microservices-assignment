package com.korbes.jarvis.inventory.processing.service.business.service.impl;

import com.korbes.jarvis.inventory.processing.service.business.mapper.InventoryMapper;
import com.korbes.jarvis.inventory.processing.service.business.service.InventoryViewerService;
import com.korbes.jarvis.inventory.processing.service.data.repository.InventoryBatchRepository;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryBatchDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
public class InventoryViewerServiceImpl implements InventoryViewerService {
  private final InventoryBatchRepository inventoryBatchRepository;

  @Override
  public List<InventoryBatchDTO> fetchInventoryBatches(Long productId) {
    try {
      var inventoryBatches = inventoryBatchRepository.findByProductProductIdOrderByExpiryDate(productId);
      if (CollectionUtils.isEmpty(inventoryBatches)) {
        System.out.println("No batches Found for productId " + productId);
        return new ArrayList<>();
      }
      return inventoryBatches.stream().map(InventoryMapper.INSTANCE::mapToInventoryBatchDTO).toList();
    } catch (Exception e) {
      throw new KorbesServiceRunTimeException(SYSTEM_ERROR + e.getMessage(), e);
    }
  }
}
