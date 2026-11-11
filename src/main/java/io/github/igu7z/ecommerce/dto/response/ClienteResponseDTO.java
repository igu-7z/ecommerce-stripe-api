package io.github.igu7z.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.igu7z.ecommerce.entity.Cliente;

@JsonPropertyOrder({ "id", "nome", "email" })
public class ClienteResponseDTO {

    private Long id;

    private String nome;

    private String email;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

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
