package br.com.pdv.api.service;

import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repo) {
        clienteRepository = repo;
    }
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}
