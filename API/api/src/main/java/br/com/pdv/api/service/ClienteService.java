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

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);

    }

    public Cliente deletarCliente(Integer id) {
        Cliente cliente = buscarPorId(id);

        if (cliente == null) {
            return null;
        }
        clienteRepository.delete(cliente);
        return cliente;
    }


}
