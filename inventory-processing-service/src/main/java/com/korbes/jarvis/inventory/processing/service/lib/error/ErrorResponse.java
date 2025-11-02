package com.korbes.jarvis.inventory.processing.service.lib.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
  private LocalDateTime localDateTime;
  private String message;
  private String details;
}
