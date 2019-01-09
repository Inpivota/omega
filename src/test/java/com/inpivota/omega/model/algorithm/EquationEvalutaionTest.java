package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EquationEvalutaionTest {

    private Constant constant1 = new Constant("one", 0, new BigDecimal(1));
    private Constant constant2 = new Constant("two", 2, new BigDecimal(2));

    @Test
    public void evaluateAddition() {

        Equation onePlusTwo = new Equation();
        onePlusTwo.setName("Test 1 + 2");
        onePlusTwo.setDescription("add 2 plus 1 as constants");
        List<Constant> constants = new ArrayList<>();
        constants.add(constant1);
        constants.add(constant2);
        onePlusTwo.setConstants(constants);
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(OperationType.ADD, 1));
        onePlusTwo.setOperations(operations);

        assertEquals(new BigDecimal(3), onePlusTwo.evaluate());
    }

    @Test
    public void evaluateSubtraction() {

        Equation oneMinusTwo = new Equation();
        oneMinusTwo.setName("1 - 2");
        oneMinusTwo.setConstants(Arrays.asList(constant1,constant2));
        oneMinusTwo.setOperations(Collections.singletonList(new Operation(OperationType.SUBTRACT, 1)));

        assertEquals(new BigDecimal(-1), oneMinusTwo.evaluate());
    }
}