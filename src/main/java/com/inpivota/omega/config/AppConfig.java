package com.inpivota.omega.config;

import com.inpivota.omega.model.algorithm.Equation;
import com.inpivota.omega.repository.algorithm.EquationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            Equation equation = new Equation();
            equation.setName("Test Constants");
            equation.setDescription("add 2 plus 2 as constants");
            equationRepository.save(equation);

            System.out.println(">>>>>>>>>>>>>>>>>>> RUNNING EQUATIONS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(equation.getName());
            System.out.println(equation.evaluate());
            System.out.println();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> END EQUATIONS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        };
    }
}
