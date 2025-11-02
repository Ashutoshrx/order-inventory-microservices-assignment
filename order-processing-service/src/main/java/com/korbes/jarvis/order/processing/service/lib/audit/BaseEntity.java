package com.korbes.jarvis.order.processing.service.lib.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditListener.class)
@Getter
@Setter
public class BaseEntity<T> {

  @Column(name = "date_of_creation", nullable = false, updatable = false)
  private LocalDateTime creationDate;
  @Column(name = "date_of_last_update", nullable = false)
  private LocalDateTime updatedDate;
}