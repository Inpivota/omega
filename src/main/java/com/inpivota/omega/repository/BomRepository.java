package com.inpivota.omega.repository;

import com.inpivota.omega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BomRepository extends JpaRepository<Product, UUID> {
}
