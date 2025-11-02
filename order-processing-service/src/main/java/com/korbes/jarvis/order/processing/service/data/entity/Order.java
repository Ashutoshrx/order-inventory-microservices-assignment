package com.korbes.jarvis.order.processing.service.data.entity;

import com.korbes.jarvis.order.processing.service.lib.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Order> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long orderId;
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "order_date")
  private LocalDateTime orderDate;
  @Column(name = "order_quantity")
  private Long orderQuantity;
}