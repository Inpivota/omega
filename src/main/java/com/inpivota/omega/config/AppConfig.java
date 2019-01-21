package com.inpivota.omega.config;

import com.inpivota.omega.enums.OperationType;
import com.inpivota.omega.model.algorithm.Constant;
import com.inpivota.omega.model.algorithm.Equation;
import com.inpivota.omega.model.algorithm.Operation;
import com.inpivota.omega.repository.algorithm.EquationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

    private EquationRepository equationRepository;

    @Autowired
    public AppConfig(
            EquationRepository equationRepository
    ) {
        this.equationRepository = equationRepository;
    }


    @Bean
    public CommandLineRunner testData(EquationRepository equationRepository) {
        return args -> {
//            Constant constant1 = new Constant("one", 0, new BigDecimal(1));
//            Constant constant2 = new Constant("two", 2, new BigDecimal(2));
//
//            Equation onePlusTwo = new Equation();
//            onePlusTwo.setName("Test 1 + 2");
//            onePlusTwo.setDescription("add 2 plus 1 as constants");
//            List<Constant> constants = new ArrayList<>();
//            constants.add(constant1);
//            constants.add(constant2);
//            onePlusTwo.setConstants(constants);
//            List<Operation> operations = new ArrayList<>();
//            operations.add(new Operation(OperationType.ADD, 1));
//            onePlusTwo.setOperations(operations);
//
//            Equation oneMinusTwo = new Equation();
//            oneMinusTwo.setName("1 - 2");
//            oneMinusTwo.setConstants(Arrays.asList(constant1,constant2));
//            oneMinusTwo.setOperations(Collections.singletonList(new Operation(OperationType.SUBTRACT, 1)));
//
//            System.out.println(">>>>>>>>>>>>>>>>>>> RUNNING EQUATIONS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//            System.out.println();
//            System.out.println(onePlusTwo.getName() + " evaluates to: " + onePlusTwo.evaluate());
//            System.out.println(oneMinusTwo.getName() + " evaluates to: " + oneMinusTwo.evaluate());
//            System.out.println();
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>> END EQUATIONS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        };
    }
}
