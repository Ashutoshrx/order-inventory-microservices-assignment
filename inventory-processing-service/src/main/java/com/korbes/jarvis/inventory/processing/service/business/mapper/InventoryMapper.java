package com.korbes.jarvis.inventory.processing.service.business.mapper;

import com.korbes.jarvis.inventory.processing.service.data.entity.InventoryBatch;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryBatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface InventoryMapper {
  InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

  @Mapping(target = "productName", source = "product.productName")
  @Mapping(target = "productDescription", source = "product.productDescription")
  @Mapping(target = "batchNumber", source = "batchNumber")
  @Mapping(target = "expiryDate", source = "expiryDate")
  @Mapping(target = "quantityAvailable", source = "quantityAvailable")
  InventoryBatchDTO mapToInventoryBatchDTO(InventoryBatch inventoryBatches);
}
