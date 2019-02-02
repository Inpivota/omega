package com.inpivota.omega.repository;

import com.inpivota.omega.model.InventoryItem;
import com.inpivota.omega.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    InventoryItem findByProduct(Product product);
    List<InventoryItem> findAllByProduct (Product product);
    InventoryItem findByProductAndLocationName (Product product, String locationName);
}
