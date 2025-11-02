package com.korbes.jarvis.order.processing.service.business;

import com.korbes.jarvis.order.processing.service.data.entity.Order;
import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderMapperTest {
  @InjectMocks
  private OrderMapperImpl orderMapper;
  private OrderRequestDTO orderRequest;

  @BeforeEach
  void setUp() {
    orderRequest = new OrderRequestDTO();
    orderRequest.setQuantity(1);
    orderRequest.setProductId(1L);
  }

  @Test
  void mapToOrder() {
    Order actual = orderMapper.mapToOrder(orderRequest);
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(orderRequest.getProductId(), actual.getProductId());
    Assertions.assertEquals(orderRequest.getQuantity().longValue(), actual.getOrderQuantity());
    Assertions.assertEquals(LocalDate.now(), actual.getOrderDate().toLocalDate());
  }

  @Test
  void mapToOrder_whenNull() {
    Order actual = orderMapper.mapToOrder(null);
    Assertions.assertNull(actual);
  }
}