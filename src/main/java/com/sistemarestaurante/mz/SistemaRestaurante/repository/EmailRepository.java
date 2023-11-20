package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.sistemarestaurante.mz.SistemaRestaurante.model.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailDetails, Long> {
}
