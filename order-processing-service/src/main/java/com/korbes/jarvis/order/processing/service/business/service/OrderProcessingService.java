package com.korbes.jarvis.order.processing.service.business.service;

import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;

public interface OrderProcessingService {
  void placeOrder(OrderRequestDTO orderRequest);
}
