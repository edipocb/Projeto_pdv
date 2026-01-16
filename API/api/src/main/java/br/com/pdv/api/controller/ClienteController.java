package br.com.pdv.api.controller;

import br.com.pdv.api.dto.UsuarioDTO.CadastrarClienteDTO;
import br.com.pdv.api.dto.UsuarioDTO.ClienteResponseDTO;
import br.com.pdv.api.dto.UsuarioDTO.ListarClientesDTO;
import br.com.pdv.api.exception.EmailJaCadastradoException;
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
    public ResponseEntity<List<ListarClientesDTO>> listarClientes(){
        List<ListarClientesDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody CadastrarClienteDTO cliente){
        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
//        clienteService.cadastrarCliente(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setNome(clienteSalvo.getNome());
        response.setEmail(clienteSalvo.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<String> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
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

//    @GetMapping("/buscarPorEmail{email}")
//    public ResponseEntity<?> buscarPorEmail(@PathVariable String email){
//        Cliente cliente = clienteService.buscarPorEmail(email);
//        if (cliente == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente " + email + " não encontrado!");
//        }
//        return ResponseEntity.ok(cliente);
//    }

}
