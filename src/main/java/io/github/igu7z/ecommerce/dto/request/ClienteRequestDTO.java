package io.github.igu7z.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {

    @NotBlank(message = "O nome do cliente não pode estar em branco")
    private String nome;

    @NotBlank(message = "O email do cliente não pode estar em branco")
    @Email(message = "o e-mail do cliente precisa ter um formato válido")
    private String email;

    @NotBlank(message = "A senha do cliente não pode estar em branco")
    private String senha;

    public ClienteRequestDTO() {
    }
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
}
