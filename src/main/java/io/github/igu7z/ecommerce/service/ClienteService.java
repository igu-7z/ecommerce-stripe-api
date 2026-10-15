package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente){
        if (cliente.getNome() == null || cliente.getNome().isEmpty()){
            throw new RuntimeException("O nome do cliente não pode ser nulo ou vazio.");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
    }

    public void deletarPorId(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    public Cliente atualizarPorId(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarPorId(id);

        if (clienteAtualizado.getNome() != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
        }

        if (clienteAtualizado.getEmail() != null) {
            clienteExistente.setEmail(clienteAtualizado.getEmail());
        }

        if (clienteAtualizado.getSenha() != null) {
            clienteExistente.setSenha(clienteAtualizado.getSenha());
        }

        return clienteRepository.save(clienteExistente);
    }
}
