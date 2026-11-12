package io.github.igu7z.ecommerce.dto.response;

import io.github.igu7z.ecommerce.entity.Categoria;

public class CategoriaResponseDTO {

    private Long id;
    private String nome;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
