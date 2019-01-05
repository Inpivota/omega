package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
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
    private List<Variable> variables;
    @ManyToMany
    private List<Equation> equations;
    @ManyToMany
    private List<Operation> operations;

    public BigDecimal evaluate(BigDecimal... variableValues) {
        BigDecimal output = BigDecimal.ZERO;

        return output;
    }

    @Override
    public String getUiLabel() {
        return null;
    }
}
