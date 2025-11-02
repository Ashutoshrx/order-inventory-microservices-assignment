package com.korbes.jarvis.order.processing.service.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ActionType {
  UPDATE_STOCK("UPDATE_STOCK"),ADD_STOCK("ADD_STOCK");

  private final String value;

  public static ActionType getByValue(String code){
    return Arrays.stream(ActionType.values()).filter(actionType -> actionType.getValue().equalsIgnoreCase(code)).
            findFirst().orElseThrow();
  }
}