package com.inpivota.omega.repository;

import com.inpivota.omega.enums.RoleName;
import com.inpivota.omega.model.common.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(final RoleName name);
}
