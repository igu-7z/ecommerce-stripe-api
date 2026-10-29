package io.github.igu7z.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.igu7z.ecommerce.entity.Cliente;

@JsonPropertyOrder({ "id", "nome", "email" })
public class ClienteResponseDTO {

    private Long id;

    private String nome;

    private String email;

    // 1. Construtor vazio obrigatório
    public ClienteResponseDTO() {
    }

    // 2. Construtor facilitador: recebe a entidade Cliente e copia os dados para o DTO
    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    // 3. Getters para que o Spring consiga ler os dados e transformá-los em JSON
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
