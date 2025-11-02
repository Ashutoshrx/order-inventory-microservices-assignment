package com.korbes.jarvis.order.processing.service.business;

import com.korbes.jarvis.order.processing.service.data.entity.Order;
import com.korbes.jarvis.order.processing.service.dto.OrderRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = LocalDateTime.class)
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "orderDate", expression = "java(LocalDateTime.now())")
  @Mapping(target = "orderQuantity", source = "quantity")
  @Mapping(target = "orderId", ignore = true)
  @Mapping(target = "creationDate", ignore = true)
  @Mapping(target = "updatedDate", ignore = true)
  Order mapToOrder(OrderRequestDTO orderRequest);
}
