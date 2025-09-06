package br.com.pdv.api.controller;

import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService service) {
        clienteService = service;
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }
}
