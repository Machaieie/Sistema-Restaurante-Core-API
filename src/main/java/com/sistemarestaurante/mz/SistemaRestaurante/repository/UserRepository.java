package com.sistemarestaurante.mz.SistemaRestaurante.repository;

import com.google.common.base.Optional;
import com.sistemarestaurante.mz.SistemaRestaurante.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsernameAndEnabled(String username, boolean enabled);
	public Optional<User> findByUsernameAndTextPassword(String username, String password);
	public Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Optional<User> findByConfirmationToken(String token);
	Optional<User> findByEmail(String email);
}
