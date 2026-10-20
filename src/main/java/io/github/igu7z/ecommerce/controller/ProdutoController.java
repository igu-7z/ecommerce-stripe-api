package io.github.igu7z.ecommerce.controller;

import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id) {
        produtoService.deletarPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarPorId (@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoService.atualizarPorId(id, produtoAtualizado);
    }
}
