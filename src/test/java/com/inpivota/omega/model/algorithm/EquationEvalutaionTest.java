package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import org.apache.tomcat.util.bcel.Const;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EquationEvalutaionTest {

    MathContext mathContext = new MathContext(10);


    Operation plus = new Operation(OperationType.ADD, 0);
    Operation minus = new Operation(OperationType.SUBTRACT, 0);
    Operation times = new Operation(OperationType.MULTIPLY, 0);
    Operation divide = new Operation(OperationType.DIVIDE, 0);

    private Constant constant1 = new Constant("one", 0, new BigDecimal(1));
    private Constant constant2 = new Constant("two", 0, new BigDecimal(2));
    private Constant constant3 = new Constant("three", 0, new BigDecimal(3));
    private Constant constant4 = new Constant("four", 0, new BigDecimal(4));

    private Variable variable1 = new Variable("var1", "the first variable", 0);
    private Variable variable2 = new Variable("var2", "the second variable", 0);

    @Test
    public void evaluateAddition() {
        constant1.setPosition(0);
        constant2.setPosition(2);
        Equation onePlusTwo = new Equation();
        onePlusTwo.setName("Test 1 + 2");
        onePlusTwo.setDescription("add 2 plus 1 as constants");
        onePlusTwo.setConstants(Arrays.asList(constant1, constant2));
        onePlusTwo.setOperations(Collections.singletonList(new Operation(OperationType.ADD, 1)));

        assertEquals(new BigDecimal(3), onePlusTwo.evaluate());
    }

    @Test
    public void evaluateSubtraction() {
        constant1.setPosition(0);
        constant2.setPosition(2);
        Equation oneMinusTwo = new Equation();
        oneMinusTwo.setName("1 - 2");
        oneMinusTwo.setConstants(Arrays.asList(constant1, constant2));
        oneMinusTwo.setOperations(Collections.singletonList(new Operation(OperationType.SUBTRACT, 1)));

        assertEquals(new BigDecimal(-1), oneMinusTwo.evaluate());
    }

    @Test
    public void evaluateMultiplication() {
        constant3.setPosition(2);
        constant2.setPosition(0);
        Equation twoTimesThree = new Equation();
        twoTimesThree.setName("2 * 3");
        twoTimesThree.setConstants(Arrays.asList(constant3, constant2));
        twoTimesThree.setOperations(Collections.singletonList(new Operation(OperationType.MULTIPLY, 1)));

        assertEquals(new BigDecimal(6), twoTimesThree.evaluate());
    }

    @Test
    public void evaluateDivision() {
        Equation fourDividedByTwo = new Equation();
        constant4.setPosition(0);
        Operation operation = new Operation(OperationType.DIVIDE, 1);
        constant2.setPosition(2);
        fourDividedByTwo.setName("4 / 2");
        fourDividedByTwo.setConstants(Arrays.asList(constant4, constant2));
        fourDividedByTwo.setOperations(Collections.singletonList(operation));

        assertEquals(new BigDecimal(2), fourDividedByTwo.evaluate());
    }

    @Test
    public void evaluateComplexDivision() {
        Equation fourDividedByThree = new Equation();
        constant4.setPosition(0);
        Operation operation = new Operation(OperationType.DIVIDE, 1);
        constant3.setPosition(2);
        fourDividedByThree.setName("4 / 3");
        fourDividedByThree.setConstants(Arrays.asList(constant4, constant3));
        fourDividedByThree.setOperations(Collections.singletonList(operation));

        assertEquals(new BigDecimal(1.3333333333, mathContext), fourDividedByThree.evaluate());
    }

    @Test
    public void evaluateMultipleMixed() {
        Equation fourPlusThreeTimesTwo = new Equation();
        constant4.setPosition(0);
        Operation operation1 = new Operation(OperationType.ADD, 1);
        constant3.setPosition(2);
        Operation operation2 = new Operation(OperationType.MULTIPLY, 3);
        constant2.setPosition(4);
        fourPlusThreeTimesTwo.setName("4 + 3 * 2");
        fourPlusThreeTimesTwo.setConstants(Arrays.asList(constant4, constant3, constant2));
        fourPlusThreeTimesTwo.setOperations(Arrays.asList(operation1,operation2));

        assertEquals(new BigDecimal(10, mathContext), fourPlusThreeTimesTwo.evaluate());
    }

    @Test
    public void evaluateMultipleMixedAgain() {
        Equation longEquation = new Equation();
        constant1.setPosition(0);
        plus.setPosition(1);
        constant2.setPosition(2);
        times.setPosition(3);
        constant3.setPosition(4);
        minus.setPosition(5);
        constant4.setPosition(6);
        Operation divideB = new Operation(OperationType.DIVIDE,7);
        Constant constant2B = new Constant("2b", 8, new BigDecimal(2));
        longEquation.setName("1+2*3-4/2");
        longEquation.setConstants(Arrays.asList(constant4, constant3, constant2, constant1, constant2B));
        longEquation.setOperations(Arrays.asList(plus,times,divideB,minus));

        assertEquals(new BigDecimal(5, mathContext), longEquation.evaluate());
    }
    //1+2*3-4/2
}