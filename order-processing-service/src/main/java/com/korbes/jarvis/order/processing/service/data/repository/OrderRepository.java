package com.korbes.jarvis.order.processing.service.data.repository;

import com.korbes.jarvis.order.processing.service.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
