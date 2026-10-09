package io.github.igu7z.ecommerce.repository;

import io.github.igu7z.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
