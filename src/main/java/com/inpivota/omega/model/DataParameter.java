package com.inpivota.omega.model;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * This class is used to for the reports and data manipulation
 * Example: DataParameter {name: "Amazon Shipping Time", description: "this is how long it takes items to get to amazon from our warehouse", value: 5}
 * Note: this does not strike me as the best way to handle the data for reports.
 */
@Data
@Entity
public class DataParameter extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal value = new BigDecimal(0);

    @Override
    public String getUiLabel() {
        return name;
    }
}
