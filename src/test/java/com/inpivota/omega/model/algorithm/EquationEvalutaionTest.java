package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EquationEvalutaionTest {

        private Constant constant1 = new Constant("one", 0, new BigDecimal(1));
        private Constant constant2 = new Constant("two", 0, new BigDecimal(2));
        private Constant constant3 = new Constant("three", 0, new BigDecimal(3));

    @Test
    public void evaluateAddition() {
        constant1.setPosition(0);
        constant2.setPosition(2);
        Equation onePlusTwo = new Equation();
        onePlusTwo.setName("Test 1 + 2");
        onePlusTwo.setDescription("add 2 plus 1 as constants");
        onePlusTwo.setConstants(Arrays.asList(constant1,constant2));
        onePlusTwo.setOperations(Collections.singletonList(new Operation(OperationType.ADD, 1)));

        assertEquals(new BigDecimal(3), onePlusTwo.evaluate());
    }

    @Test
    public void evaluateSubtraction() {
        constant1.setPosition(0);
        constant2.setPosition(2);
        Equation oneMinusTwo = new Equation();
        oneMinusTwo.setName("1 - 2");
        oneMinusTwo.setConstants(Arrays.asList(constant1,constant2));
        oneMinusTwo.setOperations(Collections.singletonList(new Operation(OperationType.SUBTRACT, 1)));

        assertEquals(new BigDecimal(-1), oneMinusTwo.evaluate());
    }

    @Test
    public void evaluateMultiplication() {
        constant3.setPosition(2);
        constant2.setPosition(0);
        Equation twoTimesThree = new Equation();
        twoTimesThree.setName("2 * 3");
        twoTimesThree.setConstants(Arrays.asList(constant3,constant2));
        twoTimesThree.setOperations(Collections.singletonList(new Operation(OperationType.MULTIPLY, 1)));

        assertEquals(new BigDecimal(6), twoTimesThree.evaluate());
    }
}