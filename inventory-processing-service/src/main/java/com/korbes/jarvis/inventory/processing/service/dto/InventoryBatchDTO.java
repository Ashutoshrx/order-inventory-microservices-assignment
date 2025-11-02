package com.korbes.jarvis.inventory.processing.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class InventoryBatchDTO implements Serializable {
  private String productName;
  private String productDescription;
  private String batchNumber;
  private LocalDate expiryDate;
  private Integer quantityAvailable;
}
