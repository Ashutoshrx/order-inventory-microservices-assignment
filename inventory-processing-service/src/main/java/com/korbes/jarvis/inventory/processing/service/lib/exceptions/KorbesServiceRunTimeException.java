package com.korbes.jarvis.inventory.processing.service.lib.exceptions;

public class KorbesServiceRunTimeException extends RuntimeException {

  public KorbesServiceRunTimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public KorbesServiceRunTimeException(String message) {
    super(message);
  }
}
