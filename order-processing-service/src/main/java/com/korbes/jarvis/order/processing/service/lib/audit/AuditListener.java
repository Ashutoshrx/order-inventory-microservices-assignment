package com.korbes.jarvis.order.processing.service.lib.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {

  @PrePersist
  public void setCreationDate(Object entity) {
    if (entity instanceof BaseEntity base) {
      base.setCreationDate(LocalDateTime.now());
      base.setUpdatedDate(LocalDateTime.now());
    }
  }

  @PreUpdate
  public void setUpdateDate(Object entity) {
    if (entity instanceof BaseEntity base) {
      base.setUpdatedDate(LocalDateTime.now());
    }
  }
}
