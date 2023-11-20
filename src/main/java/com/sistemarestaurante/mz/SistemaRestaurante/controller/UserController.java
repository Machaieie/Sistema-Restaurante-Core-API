package com.sistemarestaurante.mz.SistemaRestaurante.controller;

import com.sistemarestaurante.mz.SistemaRestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.mz.SistemaRestaurante.model.User;
import com.sistemarestaurante.mz.SistemaRestaurante.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid User user) throws ResourceNotFoundException {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setTextPassword(user.getTextPassword());
        foundUser.setConfirmationToken(user.getConfirmationToken());

        final User updatedUser = userRepository.save(foundUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
    }
}
