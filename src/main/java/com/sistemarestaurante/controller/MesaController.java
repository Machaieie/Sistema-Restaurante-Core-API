package com.sistemarestaurante.controller;

import com.sistemarestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.model.Mesa;
import com.sistemarestaurante.repository.MesaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public List<Mesa> getAllMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return mesas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mesa com o ID:: " + id + " não encontrada"));
        return ResponseEntity.ok().body(mesa);
    }

    @PostMapping
    public Mesa createNewMesa(@Valid @RequestBody Mesa mesa) {
        Mesa savedMesa = mesaRepository.save(mesa);
        return savedMesa;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMesa(@PathVariable(value = "id") UUID id, @RequestBody @Valid Mesa mesa) throws ResourceNotFoundException {
        Mesa foundMesa = mesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mesa com o ID:: " + id + " não encontrada"));

        foundMesa.setNumLugares(mesa.getNumLugares());
        foundMesa.setEstado(mesa.isEstado());
        foundMesa.setPedidos(mesa.getPedidos());
        foundMesa.setReserva(mesa.getReserva());

        final Mesa updatedMesa = mesaRepository.save(foundMesa);
        return ResponseEntity.ok(updatedMesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMesa(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mesa com o ID:: " + id + " não encontrada"));
        mesaRepository.delete(mesa);
        return ResponseEntity.status(HttpStatus.OK).body("Mesa apagada com sucesso");
    }
}
