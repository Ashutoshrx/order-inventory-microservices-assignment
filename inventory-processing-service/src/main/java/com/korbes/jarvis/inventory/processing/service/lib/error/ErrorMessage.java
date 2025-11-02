package com.korbes.jarvis.inventory.processing.service.lib.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
  public static final String SYSTEM_ERROR = "Something went wrong: ";
  public static final String INVALID_ACTION = "Action is not valid: ";
  public static final String RESOURCE_DOES_NOT_EXIST = "Resource Doesn't exist";
}