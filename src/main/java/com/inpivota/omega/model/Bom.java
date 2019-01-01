package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Bom  extends BaseEntity {

    private double quantity;
    private String note;

    @OneToOne
    private Product product;

    //Map to raw good. I believe we want a different table for all of our raw goods
    @OneToOne
    private Product raw_product;

    public Bom() {
    }

    //A product could have two sets of BOMs. For example: if we build a product to ship to amazon we use one type of box
    // but if we send it strait to the customer we use another.
    //We need some way of having two BOMs for one product.

    @Override
    public String getUiLabel() {
        return product.getUiLabel();
    }
}
