package com.korbes.jarvis.inventory.processing.service.business.factory;

import com.korbes.jarvis.inventory.processing.service.business.enums.ActionType;
import com.korbes.jarvis.inventory.processing.service.business.service.InventoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryActionHandler {
  private final InventoryHandler inventoryUpdateHandler;

  public InventoryHandler fetchHandler(String action) {
    ActionType actionType = ActionType.getByValue(action);
    return switch (actionType) {
      case UPDATE_STOCK -> inventoryUpdateHandler;
      case ADD_STOCK, EMPTY_STOCK -> null;
    };
  }
}