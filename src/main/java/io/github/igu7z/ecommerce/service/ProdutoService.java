package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O preço do produto deve ser maior que zero.");
        }

        return produtoRepository.save(produto);
    }
}
