package com.inpivota.omega.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Flag extends BaseEntity {

    private String name;

    @ManyToOne
    private FlagCategory flagCategory;

    @Override
    public String getUiLabel() {
        return name;
    }
}
