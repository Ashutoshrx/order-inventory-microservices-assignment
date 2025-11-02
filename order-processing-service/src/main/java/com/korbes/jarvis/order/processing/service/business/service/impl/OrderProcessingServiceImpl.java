package com.korbes.jarvis.order.processing.service.business.service.impl;

import com.korbes.jarvis.order.processing.service.business.OrderMapper;
import com.korbes.jarvis.order.processing.service.business.enums.ActionType;
import com.korbes.jarvis.order.processing.service.business.service.OrderProcessingService;
import com.korbes.jarvis.order.processing.service.data.entity.Order;
import com.korbes.jarvis.order.processing.service.data.repository.OrderRepository;
import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;
import com.korbes.jarvis.order.processing.service.integration.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.order.processing.service.integration.service.InventoryProcessingIntegrationService;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceIntegrationException;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.korbes.jarvis.order.processing.service.lib.error.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {
  private final InventoryProcessingIntegrationService inventoryProcessingIntegrationService;
  private final OrderRepository orderRepository;

  @Override
  @Transactional
  public void placeOrder(OrderRequestDTO orderRequest) {
    try {
      System.out.println("Started placing order");
      //update inventory
      updateInventory(orderRequest);
      //save newOrder entity with currentDate.
      Order newOrder = OrderMapper.INSTANCE.mapToOrder(orderRequest);
      orderRepository.save(newOrder);
      System.out.println("Successfully placed order");
    } catch (KorbesServiceIntegrationException e) {
      throw new KorbesServiceRunTimeException(e.getMessage(), e);
    } catch (Exception e) {
      throw new KorbesServiceRunTimeException(SYSTEM_ERROR + e.getMessage(), e);
    }
  }

  private void updateInventory(OrderRequestDTO orderRequest) throws KorbesServiceIntegrationException {
    InventoryUpdateRequestDTO inventoryUpdateRequest = InventoryUpdateRequestDTO.builder().
            productId(orderRequest.getProductId()).quantity(orderRequest.getQuantity()).build();
    inventoryProcessingIntegrationService.updateInventory(inventoryUpdateRequest, ActionType.UPDATE_STOCK.getValue());
    System.out.println("Successfully updated inventory");
  }
}
