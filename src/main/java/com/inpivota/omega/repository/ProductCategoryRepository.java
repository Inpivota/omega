package com.inpivota.omega.repository;

import com.inpivota.omega.model.OrderLineItem;
import com.inpivota.omega.model.Product;
import com.inpivota.omega.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
