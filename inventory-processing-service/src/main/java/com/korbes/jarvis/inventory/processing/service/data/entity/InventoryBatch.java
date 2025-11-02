package com.korbes.jarvis.inventory.processing.service.data.entity;

import com.korbes.jarvis.inventory.processing.service.lib.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_batch")
@Getter
@Setter
public class InventoryBatch extends BaseEntity<InventoryBatch> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_batch_id")
  private Long id;
  @Column(name = "batch_nbr")
  private String batchNumber;
  @Column(name = "quantity_available")
  private Integer quantityAvailable;
  @Column(name = "expiry_date")
  private LocalDate expiryDate;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;
}
