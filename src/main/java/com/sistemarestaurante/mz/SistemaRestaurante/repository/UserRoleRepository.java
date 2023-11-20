package com.sistemarestaurante.repository;

import com.sistemarestaurante.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
