package io.github.igu7z.ecommerce.dto.response;

import io.github.igu7z.ecommerce.entity.Produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidadeEstoque;
    private BigDecimal preco;
    private String nomeCategoria;
    private Long categoriaId;

    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.preco = produto.getPreco();
        this.nomeCategoria = produto.getCategoria().getNome();

        if (produto.getCategoria() != null) {
            this.nomeCategoria = produto.getCategoria().getNome();
            this.categoriaId = produto.getCategoria().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
