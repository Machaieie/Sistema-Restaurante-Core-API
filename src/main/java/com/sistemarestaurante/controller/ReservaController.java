package com.sistemarestaurante.controller;

import com.sistemarestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.model.Reserva;
import com.sistemarestaurante.repository.ReservaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("reserva")
    public List<Reserva> getAllReservations(){
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas;
    }

    @GetMapping("reserva/{id}")
    public ResponseEntity<Reserva> getReservationById(@PathVariable(value = "id") long id) throws ResourceNotFoundException{
        Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reserva com o ID:: "+ id+ " não encontrado"));
        return ResponseEntity.ok().body(reserva);
    }

    @PostMapping("/reserva")
    public Reserva createNewReservation(@Valid @RequestBody Reserva reserva){
        Reserva saveReserva = reservaRepository.save(reserva);
         return  saveReserva;
    }

    @PutMapping("/reserva/{id}")
    public ResponseEntity<Object> updateReservation(@PathVariable(value = "id") long id, @RequestBody @Valid Reserva reserva) throws ResourceNotFoundException{
        Reserva founedReserva = reservaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reserva com o ID:: "+ id+ " não encontrado"));
        founedReserva.setDia(reserva.getDia());
        founedReserva.setHora(reserva.getHora());
        founedReserva.setMailCliente(reserva.getMailCliente());
        founedReserva.setNomeCliente(reserva.getNomeCliente());
        founedReserva.setTelefoneCliente(reserva.getTelefoneCliente());
        founedReserva.setMesas(reserva.getMesas());
        founedReserva.setPagamentos(reserva.getPagamentos());
        final Reserva updatedReservation = reservaRepository.save(founedReserva);
        return  ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/reserva/{id}")
    public ResponseEntity<Object> deleteProduct (@PathVariable(value="id") long id)throws ResourceNotFoundException{
        Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reserva com o ID:: "+ id+ " não encontrado"));
        reservaRepository.delete(reserva);
        return ResponseEntity.status(HttpStatus.OK).body("Reserva apagada com sucesso");
    }




}
