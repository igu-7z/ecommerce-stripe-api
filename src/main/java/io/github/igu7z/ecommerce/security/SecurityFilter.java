package io.github.igu7z.ecommerce.security;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.repository.ClienteRepository;
import io.github.igu7z.ecommerce.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ClienteRepository clienteRepository;

    public SecurityFilter(TokenService tokenService, ClienteRepository clienteRepository) {
        this.tokenService = tokenService;
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. O segurança olha o cabeçalho da requisição para tentar achar o token
        String token = recuperarToken(request);

        if (token != null) {
            // 2. Extrai o e-mail de dentro do token usando o TokenService
            String email = tokenService.validarToken(token);

            if (email != null) {
                // 3. Procura o cliente no banco de dados para confirmar que ele ainda existe
                Cliente cliente = clienteRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário do token não encontrado no banco"));

                // 4. Cria o objeto de autenticação oficial que o Spring Security entende
                var authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());

                // 5. Guarda o usuário autenticado na "Sessão" da requisição atual (Avisa o Spring: "Ele está liberado!")
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 6. Manda a requisição seguir viagem para o Controller (ou para o próximo filtro)
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        // O header vem como "Bearer eyJhbGci...". Precisamos tirar a palavra "Bearer " e deixar só o token puro.
        return authHeader.replace("Bearer ", "");
    }
}
