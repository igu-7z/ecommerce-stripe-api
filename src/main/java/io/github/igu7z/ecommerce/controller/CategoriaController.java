package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.dto.request.CategoriaRequestDTO;
import io.github.igu7z.ecommerce.dto.response.CategoriaResponseDTO;
import io.github.igu7z.ecommerce.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody @Valid CategoriaRequestDTO dto) {
        CategoriaResponseDTO categoriaSalva = categoriaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        List<CategoriaResponseDTO> categorias = categoriaService.listar();
        return ResponseEntity.ok(categorias);
    }
}
