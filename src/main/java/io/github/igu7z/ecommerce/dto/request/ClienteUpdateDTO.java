package io.github.igu7z.ecommerce.dto.request;

import jakarta.validation.constraints.Email;

public class ClienteUpdateDTO {

    private String nome;

    @Email(message = "o e-mail do cliente precisa ter um formato válido")
    private String email;

    private String senha;

    public ClienteUpdateDTO(){
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
