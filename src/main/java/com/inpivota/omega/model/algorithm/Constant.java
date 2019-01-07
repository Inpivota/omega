package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Constant extends BaseEntity {

    private String name;
    private int position;

    private BigDecimal value;

    @Override
    public String getUiLabel() {
        return null;
    }

    public Constant() {
    }

    public Constant(String name, int position, BigDecimal value) {
        this.name = name;
        this.position = position;
        this.value = value;
    }
}
