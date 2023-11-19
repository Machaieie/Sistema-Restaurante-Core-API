package com.sistemarestaurante.controller;

import com.sistemarestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.model.ItemPedido;
import com.sistemarestaurante.repository.ItemPedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/itempedidos")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping
    public List<ItemPedido> getAllItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItemPedido com o ID:: " + id + " não encontrado"));
        return ResponseEntity.ok().body(itemPedido);
    }

    @PostMapping
    public ItemPedido createNewItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
        ItemPedido savedItemPedido = itemPedidoRepository.save(itemPedido);
        return savedItemPedido;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItemPedido(@PathVariable(value = "id") UUID id, @RequestBody @Valid ItemPedido itemPedido) throws ResourceNotFoundException {
        ItemPedido foundItemPedido = itemPedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItemPedido com o ID:: " + id + " não encontrado"));

        foundItemPedido.setNome(itemPedido.getNome());
        foundItemPedido.setQuantidade(itemPedido.getQuantidade());
        foundItemPedido.setPreco(itemPedido.getPreco());
        foundItemPedido.setPedido(itemPedido.getPedido());

        final ItemPedido updatedItemPedido = itemPedidoRepository.save(foundItemPedido);
        return ResponseEntity.ok(updatedItemPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItemPedido(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ItemPedido com o ID:: " + id + " não encontrado"));
        itemPedidoRepository.delete(itemPedido);
        return ResponseEntity.status(HttpStatus.OK).body("ItemPedido apagado com sucesso");
    }
}
