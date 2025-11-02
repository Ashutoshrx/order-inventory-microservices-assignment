package com.korbes.jarvis.inventory.processing.service.lib.exceptions;


import com.korbes.jarvis.inventory.processing.service.lib.error.ValidatorError;

import java.util.List;

public class KorbesValidationException extends Exception {

  public KorbesValidationException(String message, List<ValidatorError> validatorErrors) {
    super(message);
  }

  public KorbesValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}