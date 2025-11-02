package com.korbes.jarvis.inventory.processing.service.data.repository;

import com.korbes.jarvis.inventory.processing.service.data.entity.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.korbes.jarvis.inventory.processing.service.data.constants.QueryConstants.FETCH_VALID_INVENTORY;

@Repository
public interface InventoryBatchRepository extends JpaRepository<InventoryBatch, Long> {
  List<InventoryBatch> findByProductProductIdOrderByExpiryDate(Long productId);

  @Query(value = FETCH_VALID_INVENTORY, nativeQuery = true)
  InventoryBatch findByProductProductIdAndExpiryDateAfterAndQuantityAvailableGreaterThanEqualOrderByExpiryDate(@Param(
          "productId") Long productId, @Param("quantity") Integer quantity);
}