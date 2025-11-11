package br.com.pdv.api.controller;

import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.service.ClienteService;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private final GenericResponseService responseBuilder;
    private ClienteService clienteService;

    public ClienteController(ClienteService service, GenericResponseService responseBuilder) {
        clienteService = service;
        this.responseBuilder = responseBuilder;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @DeleteMapping("/deletar{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable("id") Integer id){
        Cliente cliente = clienteService.deletarCliente(id);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente" + id + "não encontrado!");
        }
        return ResponseEntity.ok(cliente);
    }

@GetMapping("/buscarPorId{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente " + id + " não encontrado!");
        }
        return ResponseEntity.ok(cliente);
}

}
