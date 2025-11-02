package com.korbes.jarvis.order.processing.service.integration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class InventoryUpdateRequestDTO implements Serializable {
  private Long productId;
  private Integer quantity;
  private String productName;
}
