package com.inpivota.omega.model.algorithm;

import com.inpivota.omega.enums.OperationType;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class EquationEvalutaionTest {

    MathContext mathContext = new MathContext(10);


    Operation plus = new Operation(OperationType.ADD, 0);
    Operation minus = new Operation(OperationType.SUBTRACT, 0);
    Operation times = new Operation(OperationType.MULTIPLY, 0);
    Operation timesA = new Operation(OperationType.MULTIPLY, 0);
    Operation timesB = new Operation(OperationType.MULTIPLY, 0);
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

    @Test
    public void variables() {
        Equation equation = new Equation();
        equation.setName("variables");
        variable1.setPosition(0);
        plus.setPosition(1);
        variable2.setPosition(2);
        equation.setVariables(Arrays.asList(variable1,variable2));
        equation.setOperations(Collections.singletonList(plus));
        assertEquals(new BigDecimal(3), equation.evaluate(new BigDecimal[]{new BigDecimal(1), new BigDecimal(2)}));
    }

    @Test
    public void variablesAndConstants() {
        Equation equation = new Equation();
        equation.setName("variables and constants");
        constant4.setPosition(0);
        times.setPosition(1);
        variable1.setPosition(2);
        plus.setPosition(3);
        constant3.setPosition(4);
        timesA.setPosition(5);
        variable2.setPosition(6);

        equation.setVariables(Arrays.asList(variable1,variable2));
        equation.setConstants(Arrays.asList(constant4,constant3));
        equation.setOperations(Arrays.asList(timesA,times,plus));
        assertEquals(new BigDecimal(54), equation.evaluate(new BigDecimal[]{new BigDecimal(9), new BigDecimal(6)}));
    }

    private Equation getComplexChildEquation() {
        /* (2 * X - 3) */
        Equation equation = new Equation();
        Constant constantA = new Constant("", 0, new BigDecimal(2));
        Operation operationA = new Operation(OperationType.MULTIPLY, 1);
        Variable variable = new Variable("","",2);
        Operation operationB = new Operation(OperationType.SUBTRACT, 3);
        Constant constantB = new Constant("", 4, new BigDecimal(3));

        equation.setConstants(Arrays.asList(constantA,constantB));
        equation.setOperations(Arrays.asList(operationA,operationB));
        equation.setVariables(Arrays.asList(variable));

        return equation;
    }
    private Equation getSimpleChildEquation() {
        /* (2 * 5 - 3) */
        Equation equation = new Equation();
        Constant constantA = new Constant("", 0, new BigDecimal(2));
        Operation operationA = new Operation(OperationType.MULTIPLY, 1);
        Constant constantB = new Constant("",2,new BigDecimal(5));
        Operation operationB = new Operation(OperationType.SUBTRACT, 3);
        Constant constantC = new Constant("", 4, new BigDecimal(3));

        equation.setConstants(Arrays.asList(constantA,constantB,constantC));
        equation.setOperations(Arrays.asList(operationA,operationB));

        return equation;
    }

    @Test
    public void simpleChild(){
        assertEquals(new BigDecimal(7), getSimpleChildEquation().evaluate());
    }
    @Test
    public void childEquationIsAccurate(){
        assertEquals(new BigDecimal(7), getComplexChildEquation().evaluate(new BigDecimal[]{new BigDecimal(5)}));
    }

    @Test
    public void nestedSimpleEquation() {
        /*   8 * simpleChild + 3 * 4 * Y   */
        /* where Y = 5 */

        Equation parentEquation = new Equation();
        parentEquation.setName("parent equation");
        constant1.setValue(new BigDecimal(8));
        constant1.setPosition(0);
        times.setPosition(1);
        Equation childEquation = getSimpleChildEquation();
        childEquation.setPosition(2);
        plus.setPosition(3);
        constant2.setValue(new BigDecimal(3));
        constant2.setPosition(4);
        timesA.setPosition(5);
        constant3.setValue(new BigDecimal(4));
        constant3.setPosition(6);
        timesB.setPosition(7);
        variable1.setPosition(8);

        parentEquation.setVariables(Collections.singletonList(variable1));
        parentEquation.setConstants(Arrays.asList(constant1, constant2,constant3));
        parentEquation.setOperations(Arrays.asList(timesA,times,plus, timesB));
        parentEquation.setEquations(Collections.singletonList(childEquation));
        BigDecimal[] mainVariables = {new BigDecimal(5)};
        BigDecimal[] childVariables = {new BigDecimal(5)};
        assertEquals(new BigDecimal(116), parentEquation.evaluate(mainVariables, childVariables));
    }

    @Test
    public void nestedComplexEquations() {
        /*   8 * (2 * X - 3) + 3 * 4 * Y   */

        Equation parentEquation = new Equation();
        parentEquation.setName("parent equation");
        constant1.setValue(new BigDecimal(8));
        constant1.setPosition(0);
        times.setPosition(1);
        Equation childEquation = getComplexChildEquation();
        childEquation.setPosition(2);
        plus.setPosition(3);
        constant2.setValue(new BigDecimal(3));
        constant2.setPosition(4);
        timesA.setPosition(5);
        constant3.setValue(new BigDecimal(4));
        constant3.setPosition(6);
        timesB.setPosition(7);
        variable1.setPosition(8);

        parentEquation.setVariables(Collections.singletonList(variable1));
        parentEquation.setConstants(Arrays.asList(constant1, constant2,constant3));
        parentEquation.setOperations(Arrays.asList(timesA,times,plus, timesB));
        parentEquation.setEquations(Collections.singletonList(childEquation));
        BigDecimal[] mainVariables = {new BigDecimal(6)};
        BigDecimal[] childVariables = {new BigDecimal(5)};
        assertEquals(new BigDecimal(128), parentEquation.evaluate(mainVariables, childVariables));
    }

    @Ignore
    @Test
    public void squareRoot() {
        fail();
    }

    @Ignore
    @Test
    public void customExponent() {
        fail();
    }
}