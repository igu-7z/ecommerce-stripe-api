package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.dto.request.ClienteRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ClienteUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ClienteResponseDTO;
import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.exception.RecursoNaoEncontradoException;
import io.github.igu7z.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método auxiliar privado para buscar a entidade pura no banco
    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());

        // Salvamos no banco e guardamos o resultado em uma variável
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Convertemos a entidade salva para o ResponseDTO antes de retornar
        return new ClienteResponseDTO(clienteSalvo);
    }

    public List<ClienteResponseDTO> listar(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> new ClienteResponseDTO(cliente))
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));

        return new ClienteResponseDTO(cliente);
    }

    public void deletarPorId(Long id) {
        Cliente cliente = buscarEntidadePorId(id);
        clienteRepository.delete(cliente);
    }

    public ClienteResponseDTO atualizarPorId(Long id, ClienteUpdateDTO dto) {
        Cliente clienteExistente = buscarEntidadePorId(id);

        if (dto.getNome() != null) {
            clienteExistente.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            clienteExistente.setEmail(dto.getEmail());
        }

        if (dto.getSenha() != null) {
            clienteExistente.setSenha(dto.getSenha());
        }

        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
        return new ClienteResponseDTO(clienteAtualizado);
    }
}
