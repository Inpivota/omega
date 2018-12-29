package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class OrderLineItem extends BaseEntity {

    private int price;
    private int quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    @Override
    public String getUiLabel() {
        return product.getUiLabel();
    }
}
