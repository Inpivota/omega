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
    private List<Constant> constants = new ArrayList<>();
    @ManyToMany
    private List<Variable> variables = new ArrayList<>();
    @ManyToMany
    private List<Equation> equations = new ArrayList<>();
    @ManyToMany
    private List<Operation> operations = new ArrayList<>();

    public BigDecimal evaluate(BigDecimal... variableValues) {
        BigDecimal output = BigDecimal.ZERO;

        operations.sort(Comparator.comparingInt(op -> op.getType().ordinal()));
        variables.sort(Comparator.comparingInt(Variable::getPosition));

        List<BigDecimal> operationOutputs = new ArrayList<>();
        List<Integer> positionsAltered = new ArrayList<>();
        BigDecimal[] calculatedValues = new BigDecimal[operations.size()];
        int completedOperations = 0;
        int lastOperation = operations.size();
        BigDecimal[] values = new BigDecimal[constants.size() + variables.size() + equations.size() + operations.size()];

        constants.forEach(constant -> {
            values[constant.getPosition()] = constant.getValue();
        });
        variables.forEach(variable -> {
            values[variable.getPosition()] = variableValues[variables.indexOf(variable)];
        });
        equations.forEach(equation -> {
            values[equation.getPosition()] = equation.evaluate();
        });
        operations.forEach(operation -> {
            values[operation.getPosition()] = BigDecimal.ZERO;
        });

        for (Operation operation : operations) {
            int operationPosition = operation.getPosition();
            BigDecimal currentOperationOutput = BigDecimal.ZERO;
            BigDecimal value1 = BigDecimal.ZERO;
            BigDecimal value2 = BigDecimal.ZERO;

            Integer pos1 = operationPosition - 1;
            int pos2 = operationPosition + 1;

            //// check val positions if in altered positions
            //// if is not present then use the value in values[]
            //// and add the pos to the list of altered positions
            if(!positionsAltered.contains(pos1)) {
                value1 = values[pos1];
                positionsAltered.add(pos1);
            }
            else {
                value1 = calculatedValues[pos1 + 1];
            }
            if(!positionsAltered.contains(pos2)) {
                value2 = values[pos2];
                positionsAltered.add(pos2);
            }
            //// else use the values in calculatedValues
            else {
                value2 = calculatedValues[pos2 + 1];
            }
            switch (operation.getType()) {
                case ADD:
                    currentOperationOutput = value1.add(value2);
                    break;
                case SUBTRACT:
                    currentOperationOutput = value1.subtract(value2);
                    break;
                case MULTIPLY:
                    currentOperationOutput = value1.multiply(value2);
                    break;
                case DIVIDE:
                    currentOperationOutput = value1.divide(value2);
                    break;
            }
            completedOperations ++;
            if (completedOperations == lastOperation) output = currentOperationOutput;
            else calculatedValues[operationPosition] = currentOperationOutput;
        }

        return output;
    }

    @Override
    public String getUiLabel() {
        return null;
    }
}
