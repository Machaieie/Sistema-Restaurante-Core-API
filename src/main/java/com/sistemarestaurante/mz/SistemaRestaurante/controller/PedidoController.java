package com.sistemarestaurante.mz.SistemaRestaurante.controller;

import com.sistemarestaurante.mz.SistemaRestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.mz.SistemaRestaurante.model.Pedido;
import com.sistemarestaurante.mz.SistemaRestaurante.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com o ID:: " + id + " não encontrado"));
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public Pedido createNewPedido(@Valid @RequestBody Pedido pedido) {
        Pedido savedPedido = pedidoRepository.save(pedido);
        return savedPedido;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePedido(@PathVariable(value = "id") long id, @RequestBody @Valid Pedido pedido) throws ResourceNotFoundException {
        Pedido foundPedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com o ID:: " + id + " não encontrado"));
        foundPedido.setMesa(pedido.getMesa());
        foundPedido.setItemPedidos(pedido.getItemPedidos());
        final Pedido updatedPedido = pedidoRepository.save(foundPedido);
        return ResponseEntity.ok(updatedPedido);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido com o ID:: " + id + " não encontrado"));
        pedidoRepository.delete(pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido apagado com sucesso");
    }
}
