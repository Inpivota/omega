package com.inpivota.omega.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class InventoryItem extends BaseEntity {

    private String name;
    private int quantity;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Product product;

    @Override
    public String getUiLabel() {
        return name;
    }
}
