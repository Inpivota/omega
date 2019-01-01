package com.inpivota.omega.model;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RawProduct extends BaseEntity {

    private String modelNumber;
    private String finaleId;
    private String SupplierId;
    private String name;
    private String description;  // Found that we only need to set the length to 3060, instead of 255. Not sure how to do that here.
    private String notes; // Should probably increase the limit of this field to something bigger than 255.

    @Override
    public String getUiLabel() {
        return name;
    }

}
