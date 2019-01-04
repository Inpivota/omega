package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Equation extends BaseEntity {

    private String name;
    private String description;

    private int position;

    @ManyToMany
    private List<Constant> constants;
    @ManyToMany
    private List<Equation> equations;
    @ManyToMany
    private List<Operation> operations;

    /**
     * getOutput is currently commented out, because if it is present,
     * and I were to findAll Equations, then every single equation output would be calculated,
     * so that the output value would be present in the JSON.
     * It makes more sense to calculate the value in some service via a rest controller.
     */
//    public BigDecimal getOutput(){
//        return new BigDecimal("0");
//    }

    @Override
    public String getUiLabel() {
        return null;
    }
}
