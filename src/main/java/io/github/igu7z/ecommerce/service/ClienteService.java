package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.dto.request.ClienteLoginDTO;
import io.github.igu7z.ecommerce.dto.request.ClienteRequestDTO;
import io.github.igu7z.ecommerce.dto.request.ClienteUpdateDTO;
import io.github.igu7z.ecommerce.dto.response.ClienteResponseDTO;
import io.github.igu7z.ecommerce.dto.response.LoginResponseDTO;
import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.exception.CredenciaisInvalidasException;
import io.github.igu7z.ecommerce.exception.RecursoNaoEncontradoException;
import io.github.igu7z.ecommerce.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ClienteService(ClienteRepository clienteRepository,
                          PasswordEncoder passwordEncoder,
                          TokenService tokenService) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    private Cliente buscarEntidadePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com o ID: " + id));
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(clienteSalvo);
    }

    public List<ClienteResponseDTO> listar() {
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

    public LoginResponseDTO autenticar(ClienteLoginDTO dto) {
        Cliente cliente = clienteRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new CredenciaisInvalidasException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(dto.getSenha(), cliente.getSenha())) {
            throw new CredenciaisInvalidasException("E-mail ou senha inválidos");
        }

        String token = tokenService.gerarToken(cliente);

        return new LoginResponseDTO("Login realizado com sucesso!", token);
    }
}
