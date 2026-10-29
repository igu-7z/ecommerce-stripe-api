package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.dto.request.ProdutoRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ProdutoUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ProdutoResponseDTO;
import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.exception.RecursoNaoEncontradoException;
import io.github.igu7z.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private Produto buscarEntidadePorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(produto))
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));

        return new ProdutoResponseDTO(produto);
    }

    public void deletarPorId(Long id) {
        Produto produto = buscarEntidadePorId(id);
        produtoRepository.delete(produto);
    }

    public ProdutoResponseDTO atualizarPorId(Long id, ProdutoUpdateDTO dto) {
        Produto produtoExistente = buscarEntidadePorId(id);

        if (dto.getNome() != null) {
            produtoExistente.setNome(dto.getNome());
        }

        if (dto.getDescricao() != null) {
            produtoExistente.setDescricao(dto.getDescricao());
        }

        if (dto.getQuantidadeEstoque() != null) {
            produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        }

        if (dto.getPreco() != null) {
            produtoExistente.setPreco(dto.getPreco());
        }

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }
}
