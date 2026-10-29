package io.github.igu7z.ecommerce.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProdutoUpdateDTO {

    private String nome;

    private String descricao;

    @PositiveOrZero(message = "A quantidade em estoque deve ser zero ou maior")
    private Integer quantidadeEstoque;

    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    public ProdutoUpdateDTO(){
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
}