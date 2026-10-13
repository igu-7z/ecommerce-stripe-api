package io.github.igu7z.ecommerce.service;

import io.github.igu7z.ecommerce.entity.Cliente;
import io.github.igu7z.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

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
}
