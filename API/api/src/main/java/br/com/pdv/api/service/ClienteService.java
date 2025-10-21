package br.com.pdv.api.service;

import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repo) {
        clienteRepository = repo;
    }
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cl) {
        return clienteRepository.save(cl);
    }
}
