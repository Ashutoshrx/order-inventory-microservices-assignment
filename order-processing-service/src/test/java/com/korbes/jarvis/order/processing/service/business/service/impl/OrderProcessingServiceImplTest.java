package com.korbes.jarvis.order.processing.service.business.service.impl;

import com.korbes.jarvis.order.processing.service.business.OrderMapper;
import com.korbes.jarvis.order.processing.service.data.entity.Order;
import com.korbes.jarvis.order.processing.service.data.repository.OrderRepository;
import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;
import com.korbes.jarvis.order.processing.service.integration.service.InventoryProcessingIntegrationService;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceIntegrationException;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderProcessingServiceImplTest {
  @Mock
  private InventoryProcessingIntegrationService inventoryProcessingIntegrationService;
  @Mock
  private OrderRepository orderRepository;
  @Mock
  private OrderMapper orderMapper;
  @InjectMocks
  private OrderProcessingServiceImpl orderProcessingService;
  private OrderRequestDTO orderRequest;

  @BeforeEach
  void setUp() throws KorbesServiceIntegrationException {
    orderRequest = new OrderRequestDTO();
    orderRequest.setProductId(1L);
    orderRequest.setQuantity(3);
    Order order = new Order();
    order.setOrderDate(LocalDateTime.now());
    order.setProductId(1L);
    doNothing().when(inventoryProcessingIntegrationService).updateInventory(any(), anyString());
    when(orderMapper.mapToOrder(any())).thenReturn(order);
  }

  @Test
  void placeOrder() {
    Assertions.assertDoesNotThrow(() -> orderProcessingService.placeOrder(orderRequest));
  }

  @Test
  void placeOrder_throwsRunTimeException() {
    doThrow(new RuntimeException("DB failure"))
            .when(orderRepository).save(any());
    Assertions.assertThrows(KorbesServiceRunTimeException.class, () -> orderProcessingService.placeOrder(orderRequest));
  }

  @Test
  void placeOrder_throwsIntegrationRunTimeException() throws KorbesServiceIntegrationException {
    doThrow(KorbesServiceIntegrationException.class).when(inventoryProcessingIntegrationService).updateInventory(any(),
            anyString());
    Assertions.assertThrows(KorbesServiceRunTimeException.class, () -> orderProcessingService.placeOrder(orderRequest));
  }
}