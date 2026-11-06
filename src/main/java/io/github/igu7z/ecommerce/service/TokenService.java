package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    private final String secret = "minha-chave-secreta-super-segura-e-longa-para-o-atlas-commerce-2026";

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(Cliente cliente) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + 1000 * 60 * 60 * 2);

        return Jwts.builder()
                .subject(cliente.getEmail())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(getChave())
                .compact();

    }
}
