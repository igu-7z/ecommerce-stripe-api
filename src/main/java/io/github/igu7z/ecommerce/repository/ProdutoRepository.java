package io.github.igu7z.ecommerce.repository;

import io.github.igu7z.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
