package io.github.igu7z.ecommerce.repository;

import io.github.igu7z.ecommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
