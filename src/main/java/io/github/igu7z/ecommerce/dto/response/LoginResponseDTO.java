package io.github.igu7z.ecommerce.dto.response;

public class LoginResponseDTO {

    private String mensagem;
    private String token;

    public LoginResponseDTO(String mensagem, String token) {
        this.mensagem = mensagem;
        this.token = token;
    }

    public String getMensagem() { return mensagem; }
    public String getToken() { return token; }
}
