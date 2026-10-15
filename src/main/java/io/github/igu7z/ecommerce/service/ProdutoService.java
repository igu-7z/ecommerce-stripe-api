package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    public void deletarPorId(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    public Produto atualizarPorId (Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarPorId(id);

        if (produtoAtualizado.getNome() != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
        }

        if (produtoAtualizado.getDescricao() != null) {
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        }

            produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        if (produtoAtualizado.getPreco() != null) {
            produtoExistente.setPreco(produtoAtualizado.getPreco());
        }

        return produtoRepository.save(produtoExistente);
    }
}
