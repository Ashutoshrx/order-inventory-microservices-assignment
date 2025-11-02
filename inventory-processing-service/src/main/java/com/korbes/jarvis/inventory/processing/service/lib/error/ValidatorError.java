package com.korbes.jarvis.inventory.processing.service.lib.error;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class ValidatorError implements Serializable {
  private String code;
  private String message;
}
