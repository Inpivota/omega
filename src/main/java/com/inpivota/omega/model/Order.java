package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "order_record")
public class Order extends BaseEntity {

    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate shipDate;
    private int shippingCost;
    // Some way to store customer data. Eventually we will want to be able to know who are returning customers are.
    // Statues of order (Payment Processed, Packed, Shipped, ect)

//    @ManyToOne @todo  when we start tracking customer info. here is a start :P
//    private Customer customer;


    //Ship from address
    //Ship to address
    // What warehouse did the oder ship from (Ours or FBA)
//    @ManyToOne
//    private Location location;
    //Line items may ship from different warehouses?

    @OneToMany(mappedBy = "order")
    private List<OrderLineItem> orderItems;

    @Override
    public String getUiLabel() {
        return orderNumber;
    }
}
