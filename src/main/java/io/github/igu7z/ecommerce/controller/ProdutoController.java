package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.dto.request.ProdutoRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ProdutoUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ProdutoResponseDTO;
import io.github.igu7z.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoSalvo = produtoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarPorId(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoUpdateDTO dto) {
        ProdutoResponseDTO produtoAtualizado = produtoService.atualizarPorId(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }
}