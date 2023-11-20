package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.sistemarestaurante.mz.SistemaRestaurante.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
