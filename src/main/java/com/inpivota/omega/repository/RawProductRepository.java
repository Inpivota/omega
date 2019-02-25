package com.inpivota.omega.repository;

import com.inpivota.omega.model.RawProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RawProductRepository extends JpaRepository<RawProduct, UUID> {
}
