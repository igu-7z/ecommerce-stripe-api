package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.dto.request.ProdutoRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ProdutoUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ProdutoResponseDTO;
import io.github.igu7z.ecommerce.entity.Categoria;
import io.github.igu7z.ecommerce.entity.Produto;
import io.github.igu7z.ecommerce.exception.RecursoNaoEncontradoException;
import io.github.igu7z.ecommerce.repository.CategoriaRepository;
import io.github.igu7z.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    private Produto buscarEntidadePorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
    }

    @Transactional
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada!"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(categoria);

        Produto produtoSalvo = produtoRepository.save(produto);

        ProdutoResponseDTO respostaDto = new ProdutoResponseDTO(produtoSalvo);

        return respostaDto;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = buscarEntidadePorId(id); // Reutilizando nosso método auxiliar limpo!
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Produto produto = buscarEntidadePorId(id);
        produtoRepository.delete(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizarPorId(Long id, ProdutoUpdateDTO dto) {
        // 1. Buscamos o produto existente no banco (lança 404 se não achar)
        Produto produtoExistente = buscarEntidadePorId(id);

        // 2. Atualizamos os campos básicos caso venham preenchidos no DTO
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

        if (dto.getCategoriaId() != null) {
            Categoria novaCategoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

            produtoExistente.setCategoria(novaCategoria);
        }

        // 4. Salvamos as alterações (tanto dos dados quanto da categoria, se foi alterada)
        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return new ProdutoResponseDTO(produtoAtualizado);
    }
}
