package com.inpivota.omega.repository.algorithm;

import com.inpivota.omega.model.algorithm.Equation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquationRepository extends JpaRepository<Equation, UUID> {
}
