package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.dto.request.ClienteRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ClienteUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ClienteResponseDTO;
import io.github.igu7z.ecommerce.entity.Cliente;
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
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO clienteSalvo = clienteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarPorId(
            @PathVariable Long id,
            @Valid @RequestBody ClienteUpdateDTO dto) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizarPorId(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }
}
