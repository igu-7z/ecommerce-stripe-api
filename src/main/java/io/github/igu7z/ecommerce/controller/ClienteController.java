package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarPorId(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteService.atualizarPorId(id, clienteAtualizado);
    }
}
