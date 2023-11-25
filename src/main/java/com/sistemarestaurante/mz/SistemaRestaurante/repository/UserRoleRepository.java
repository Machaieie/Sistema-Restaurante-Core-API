package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.sistemarestaurante.mz.SistemaRestaurante.model.User;
import com.sistemarestaurante.mz.SistemaRestaurante.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

     //findByUser();
}
