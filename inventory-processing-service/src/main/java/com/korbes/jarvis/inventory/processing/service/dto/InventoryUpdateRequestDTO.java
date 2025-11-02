package com.korbes.jarvis.inventory.processing.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InventoryUpdateRequestDTO implements Serializable {
  private Long productId;
  @NotNull(message = "quantity is mandatory")
  private Integer quantity;
  //This is request when product is newly added to stock- we are going to use the same API which supports factory
  // design patter
  private String productName;
}
