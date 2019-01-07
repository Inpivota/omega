package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.*;

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
//        int nextVarIndex = 0;
        BigDecimal output = BigDecimal.ZERO;

        List<BigDecimal> operationOutputs = new ArrayList<>();
        operations.sort(Comparator.comparingInt(op -> op.getType().ordinal()));

        operations.forEach(operation -> {
            int operationPosition = operation.getPosition();
            BigDecimal value1 = BigDecimal.ZERO;
            BigDecimal value2 = BigDecimal.ZERO;

            Optional<Constant> constant = Optional.empty();
            for (Constant constant1 : constants) {
                if (constant1.getPosition() == operationPosition - 1) {
                    constant = Optional.of(constant1);
                    break;
                }
            }
//            Optional<Variable> variable = Optional.empty();
//            for (Variable var1 : variables) {
//                if (var1.getPosition() == operationPosition -1) {
//                    variable = Optional.of(var1);
//                    break;
//                }
//            }

            if (constant.isPresent()) value1 = constant.get().getValue();

            for (Constant constant1 : constants) {
                if (constant1.getPosition() == operationPosition + 1) {
                    constant = Optional.of(constant1);
                    break;
                }
            }
//            for (Variable var1 : variables) {
//                if (var1.getPosition() == operationPosition +1) {
//                    variable = Optional.of(var1);
//                    break;
//                }
//            }
            if (constant.isPresent()) value2 = constant.get().getValue();
//            if(variable.isPresent()) {
//                value1 = variableValues[nextVarIndex];
//                nextVarIndex ++;
//            }

            switch (operation.getType()) {
                case ADD:
                    operationOutputs.add(value1.add(value2));
                    break;
            }

        });

        for (BigDecimal operationOutput : operationOutputs) {
            output = output.add(operationOutput);
        }
        return output;
    }

    @Override
    public String getUiLabel() {
        return null;
    }
}
