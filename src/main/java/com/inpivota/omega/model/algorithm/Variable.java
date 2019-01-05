package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Variable extends BaseEntity {

    private String name;
    private String description;
    private int position;

    @Override
    public String getUiLabel() {
        return name;
    }

}
