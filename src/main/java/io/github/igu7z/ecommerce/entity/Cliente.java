package io.github.igu7z.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente não pode estar em branco")
    private String nome;

    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "O formato do email deve ser válido")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    private String senha;
}
