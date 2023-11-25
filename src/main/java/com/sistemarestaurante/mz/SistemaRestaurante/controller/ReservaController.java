package com.sistemarestaurante.mz.SistemaRestaurante.controller;

import com.sistemarestaurante.mz.SistemaRestaurante.DTO.ReservaDTO;
import com.sistemarestaurante.mz.SistemaRestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.mz.SistemaRestaurante.model.Reserva;
import com.sistemarestaurante.mz.SistemaRestaurante.repository.ReservaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


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
    public ResponseEntity createNewReservation(@Valid @RequestBody ReservaDTO reservaDTO){
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(reservaDTO, reserva);
        String codigoReserva = gerarCodigo();
        reserva.setCodigoReserva(codigoReserva);
        Reserva savedReserva = reservaRepository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReserva);
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

    public static String gerarCodigo() {
        String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char caractere = caracteres.charAt(random.nextInt(caracteres.length()));
            codigo.append(caractere);
        }
        return codigo.toString();
    }



}
