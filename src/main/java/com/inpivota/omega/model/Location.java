package com.inpivota.omega.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Location extends BaseEntity {

    private String name;

    //We will need to add sub-locations eventually

    @Override
    public String getUiLabel() {
        return name;
    }
}
