package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.math.MathContext;
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

    @Override
    public String getUiLabel() {
        return null;
    }

    /**
     * This function will use the given output to solve for the variables in the equation.
     *
     * example: equation : 4X * 3Y = 54
     * return value would be BigDecimal[9, 6]
     * meaning X = 9 and Y = 6
     *
     * @param output the number on the other side of the = sign
     * @return an array of variable values
     */
    public BigDecimal[] solveForVariables(BigDecimal output){
        return new BigDecimal[0];
    }

    /**
     *
     * @param variableValues An array of arrays. the first array is for this equation.
     *                       Subsequent arrays are for nested Equations, in order of those equation positions
     * @return the calculated output of all related constants, variables and nested equations.
     */
    public BigDecimal evaluate(BigDecimal[]... variableValues) {
        if (variables.size() > 0 && (variableValues == null || variableValues[0].length < variables.size()))
            throw new IllegalArgumentException("Insufficient number or Variable Values Provided");
        // @TODO finish adding checks
        // check for too many variableValues
        // check that all positions are sequential
        // check for the proper number of variable value arrays to match the number of equations
        // check that there is an operation between ever value,
        // check that there are not two operations next to each other
        // and other checks

        BigDecimal output = BigDecimal.ZERO;
        int totalNumberOfItems = constants.size() + variables.size() + equations.size() + operations.size();
        MathContext mathContext = new MathContext(10);
        List<Operation> opsByPosition = new ArrayList<>(operations);
        opsByPosition.sort(Comparator.comparingInt(Operation::getPosition));
        operations.sort(Comparator.comparingInt(op -> op.getType().ordinal()));
        variables.sort(Comparator.comparingInt(Variable::getPosition));
        equations.sort(Comparator.comparingInt(Equation::getPosition));

        List<BigDecimal> operationOutputs = new ArrayList<>();
        List<Integer> positionsAltered = new ArrayList<>();
        BigDecimal[] calculatedValues = new BigDecimal[operations.size()+1];
        int completedOperations = 0;
        int lastOperation = operations.size();
        BigDecimal[] values = new BigDecimal[totalNumberOfItems];

        constants.forEach(constant -> {
            values[constant.getPosition()] = constant.getValue();
        });
        variables.forEach(variable -> {
            values[variable.getPosition()] = variableValues[0][variables.indexOf(variable)];
        });
        equations.forEach(equation -> {
            values[equation.getPosition()] = equation.evaluate(variableValues[equations.indexOf(equation)+1]);
        });
        operations.forEach(operation -> {
            values[operation.getPosition()] = BigDecimal.ZERO;
        });

        for (Operation operation : operations) {
            int operationPosition = operation.getPosition();
            BigDecimal currentOperationOutput = BigDecimal.ZERO;
            BigDecimal value1;
            BigDecimal value2;

            Integer pos1 = operationPosition - 1;
            int pos2 = operationPosition + 1;

            boolean didUsePreviousCalculateValue = false;
            boolean didUseFollowingCalculateValue = false;

            /*
             check val positions if in altered positions
             if is not present then use the value in values[]
             and add the pos to the list of altered positions
            */
            if(!positionsAltered.contains(pos1)) {
                value1 = values[pos1];
                positionsAltered.add(pos1);
            }
            /*else use the values in calculatedValues*/
            else {
                value1 = calculatedValues[opsByPosition.indexOf(operation)-1];
                didUsePreviousCalculateValue = true;
            }
            if(!positionsAltered.contains(pos2)) {
                value2 = values[pos2];
                positionsAltered.add(pos2);
            }
            else {
                value2 = calculatedValues[opsByPosition.indexOf(operation)+1];
                didUseFollowingCalculateValue = true;
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
                    currentOperationOutput = value1.divide(value2, mathContext);
                    break;
            }
            completedOperations ++;
            if (completedOperations == lastOperation) output = currentOperationOutput;
            else {
                calculatedValues[opsByPosition.indexOf(operation)] = currentOperationOutput;
                if(didUsePreviousCalculateValue)
                    calculatedValues[opsByPosition.indexOf(operation) - 1] = currentOperationOutput;
                if(didUseFollowingCalculateValue)
                    calculatedValues[opsByPosition.indexOf(operation) + 1] = currentOperationOutput;
            }
        }

        return output;
    }
}
