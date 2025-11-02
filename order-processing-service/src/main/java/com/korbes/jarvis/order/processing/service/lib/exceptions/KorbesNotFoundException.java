package com.korbes.jarvis.order.processing.service.lib.exceptions;

public class KorbesNotFoundException extends RuntimeException {

  public KorbesNotFoundException(String message) {
    super(message);
  }

  public KorbesNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
