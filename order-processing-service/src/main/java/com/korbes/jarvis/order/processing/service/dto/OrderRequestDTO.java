package com.korbes.jarvis.order.processing.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderRequestDTO implements Serializable {
  private Long productId;
  private Integer quantity;
}
