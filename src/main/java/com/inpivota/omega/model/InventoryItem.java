package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
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

    @ManyToOne
    private RawProduct rawProduct;

    @Override
    public String getUiLabel() {
        return name;
    }
}
