package com.sistemarestaurante.controller;

import com.sistemarestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.model.User;
import com.sistemarestaurante.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid User user) throws ResourceNotFoundException {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setTextPassword(user.getTextPassword());
        foundUser.setConfirmationToken(user.getConfirmationToken());

        final User updatedUser = userRepository.save(foundUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com o ID:: " + id + " não encontrado"));
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
    }
}
