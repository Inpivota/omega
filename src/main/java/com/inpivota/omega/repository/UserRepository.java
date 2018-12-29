package com.inpivota.omega.repository;

import com.inpivota.omega.model.common.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(final String username);

    boolean existsByEmail(final String email);

    Optional<User> findByUsername(final String username);

    Optional<User> findByEmail(final String email);
}
