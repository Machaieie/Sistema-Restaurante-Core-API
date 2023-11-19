package com.sistemarestaurante.controller;

import com.sistemarestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.model.Pagamento;
import com.sistemarestaurante.repository.PagamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento com o ID:: " + id + " não encontrado"));
        return ResponseEntity.ok().body(pagamento);
    }

    @PostMapping
    public Pagamento createNewPagamento(@Valid @RequestBody Pagamento pagamento) {
        Pagamento savedPagamento = pagamentoRepository.save(pagamento);
        return savedPagamento;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePagamento(@PathVariable(value = "id") long id, @RequestBody @Valid Pagamento pagamento) throws ResourceNotFoundException {
        Pagamento foundPagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento com o ID:: " + id + " não encontrado"));

        foundPagamento.setDataPagamento(pagamento.getDataPagamento());
        foundPagamento.setValorTotal(pagamento.getValorTotal());
        foundPagamento.setReserva(pagamento.getReserva());

        final Pagamento updatedPagamento = pagamentoRepository.save(foundPagamento);
        return ResponseEntity.ok(updatedPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePagamento(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pagamento com o ID:: " + id + " não encontrado"));
        pagamentoRepository.delete(pagamento);
        return ResponseEntity.status(HttpStatus.OK).body("Pagamento apagado com sucesso");
    }
}
