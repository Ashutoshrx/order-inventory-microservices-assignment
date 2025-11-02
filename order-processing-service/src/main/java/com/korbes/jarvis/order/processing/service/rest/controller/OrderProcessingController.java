package com.korbes.jarvis.order.processing.service.rest.controller;

import com.korbes.jarvis.order.processing.service.business.service.OrderProcessingService;
import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderProcessingController {
  private final OrderProcessingService orderProcessingService;

  @PostMapping("/v1/order")
  public void placeOrder(@RequestBody OrderRequestDTO orderRequest) {
    orderProcessingService.placeOrder(orderRequest);
  }
}
