package com.inpivota.omega.repository;

import com.inpivota.omega.model.OrderLineItem;
import com.inpivota.omega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, UUID> {
    List<OrderLineItem> findAllByProductAndOrder_OrderDate(Product product, LocalDate date);
    List<OrderLineItem> findAllByProductAndOrder_OrderDateBetween(Product product, LocalDate startDate, LocalDate endDate);
}
