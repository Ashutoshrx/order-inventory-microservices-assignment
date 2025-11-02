package com.korbes.jarvis.inventory.processing.service.data.entity;

import com.korbes.jarvis.inventory.processing.service.lib.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product extends BaseEntity<Product> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "name")
  private String productName;
  @Column(name = "description")
  private String productDescription;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<InventoryBatch> batches = new ArrayList<>();
}
