package com.korbes.jarvis.inventory.processing.service.business.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korbes.jarvis.inventory.processing.service.business.mapper.InventoryMapper;
import com.korbes.jarvis.inventory.processing.service.data.entity.InventoryBatch;
import com.korbes.jarvis.inventory.processing.service.data.repository.InventoryBatchRepository;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryBatchDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryViewerServiceImplTest {
  @InjectMocks
  private InventoryViewerServiceImpl inventoryViewerService;
  @Mock
  private InventoryBatchRepository inventoryBatchRepository;
  @Mock
  private InventoryMapper inventoryMapper;
  private final ObjectMapper objectMapper= new ObjectMapper();
  private List<InventoryBatchDTO> inventoryBatchResponse;

  @BeforeEach
  void setUp() throws IOException {
    inventoryBatchResponse =
            objectMapper.readValue(new File("src/test/resources/inventoryBatchDTO.json"), new TypeReference<>() {
            });
    InventoryBatch batch = objectMapper.readValue(
            new File("src/test/resources/inventoryBatchEntity.json"),
            InventoryBatch.class
    );
    when(inventoryMapper.mapToInventoryBatchDTO(any())).thenReturn(inventoryBatchResponse.get(0));
    when(inventoryBatchRepository.findByProductProductIdOrderByExpiryDate(anyLong())).thenReturn(List.of(batch));
  }

  @Test
  void fetchInventoryBatches() {
    List<InventoryBatchDTO> actual = inventoryViewerService.fetchInventoryBatches(1L);
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(inventoryBatchResponse.get(0).getProductName(), actual.get(0).getProductName());
    Assertions.assertEquals(inventoryBatchResponse.get(0).getBatchNumber(), actual.get(0).getBatchNumber());
    Assertions.assertEquals(inventoryBatchResponse.get(0).getQuantityAvailable(), actual.get(0).getQuantityAvailable());
  }

  @Test
  void fetchInventoryBatches_returnEmptyResponse_whenNoRecordsFound() {
    when(inventoryBatchRepository.findByProductProductIdOrderByExpiryDate(anyLong())).thenReturn(null);
    List<InventoryBatchDTO> actual = inventoryViewerService.fetchInventoryBatches(1L);
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(0, actual.size());
  }
}