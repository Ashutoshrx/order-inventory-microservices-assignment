package com.korbes.jarvis.inventory.processing.service.business.service.impl;

import com.korbes.jarvis.inventory.processing.service.business.factory.InventoryActionHandler;
import com.korbes.jarvis.inventory.processing.service.business.service.InventoryHandler;
import com.korbes.jarvis.inventory.processing.service.business.validator.ActionValidator;
import com.korbes.jarvis.inventory.processing.service.dto.InventoryUpdateRequestDTO;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesNotFoundException;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;

import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.SYSTEM_ERROR;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryProcessingServiceImplTest {
  @Mock
  private InventoryActionHandler inventoryActionHandler;

  @Mock
  private InventoryHandler inventoryHandler;

  @InjectMocks
  private InventoryProcessingServiceImpl inventoryProcessingService;

  private InventoryUpdateRequestDTO request;

  @AfterEach
  void setUp() {
  }

  @Test
  void updateInventory() throws KorbesValidationException {
    String action = "UPDATE_STOCK";
    MockedStatic<ActionValidator> mockedValidator = mockStatic(ActionValidator.class);
    mockedValidator.when(() -> ActionValidator.validate(action)).thenAnswer(invocation -> null);

    when(inventoryActionHandler.fetchHandler(action)).thenReturn(inventoryHandler);
    inventoryProcessingService.updateInventory(request, action);
    verify(inventoryActionHandler).fetchHandler(action);
    verify(inventoryHandler).manageInventory(request);
    mockedValidator.close();
  }

  @Test
  void updateInventory_ThrowsKorbesNotFoundException() {
    String action = "ADD_STOCK";
    MockedStatic<ActionValidator> mockedValidator = mockStatic(ActionValidator.class);
    mockedValidator.when(() -> ActionValidator.validate(action)).thenAnswer(invocation -> null);
    when(inventoryActionHandler.fetchHandler(action)).thenThrow(new KorbesNotFoundException("Handler not found"));
    assertThrows(KorbesNotFoundException.class,
            () -> inventoryProcessingService.updateInventory(request, action));
    mockedValidator.close();
  }
  @Test
  void updateInventory_ThrowsKorbesServiceRunTimeException() {
    String action = "EMPTY_STOCK";
    MockedStatic<ActionValidator> mockedValidator = mockStatic(ActionValidator.class);
    mockedValidator.when(() -> ActionValidator.validate(action)).thenAnswer(invocation -> null);
    when(inventoryActionHandler.fetchHandler(action)).thenReturn(inventoryHandler);
    doThrow(new RuntimeException("Database down")).when(inventoryHandler).manageInventory(request);
    KorbesServiceRunTimeException ex = assertThrows(
            KorbesServiceRunTimeException.class,
            () -> inventoryProcessingService.updateInventory(request, action)
    );
    assertTrue(ex.getMessage().contains(SYSTEM_ERROR));
    verify(inventoryHandler).manageInventory(request);
    mockedValidator.close();
  }
}