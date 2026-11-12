package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.dto.request.CategoriaRequestDTO;
import io.github.igu7z.ecommerce.dto.response.CategoriaResponseDTO;
import io.github.igu7z.ecommerce.entity.Categoria;
import io.github.igu7z.ecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(categoriaSalva);
    }
}
