package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.dto.request.ClienteLoginDTO;
import io.github.igu7z.ecommerce.dto.response.LoginResponseDTO;
import io.github.igu7z.ecommerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final ClienteService clienteService;

    public AutenticacaoController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid ClienteLoginDTO dto) {
        LoginResponseDTO response = clienteService.autenticar(dto);

        return ResponseEntity.ok(response);
    }
}
