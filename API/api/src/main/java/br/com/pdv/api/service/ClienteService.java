package br.com.pdv.api.service;

import br.com.pdv.api.dto.UsuarioDTO.CadastrarClienteDTO;
import br.com.pdv.api.dto.UsuarioDTO.ListarClientesDTO;
import br.com.pdv.api.exception.EmailJaCadastradoException;
import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.repository.ClienteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;

    public ClienteService(PasswordEncoder passwordEncoder, ClienteRepository repo) {
        this.passwordEncoder = passwordEncoder;
        clienteRepository = repo;
    }

    public List<ListarClientesDTO> listarTodos() {
        List<Cliente>  clientes = clienteRepository.findAll();

        return clientes.stream().map(this::converterParaListagemDTO)
                .collect(Collectors.toList());
    }

    public ListarClientesDTO converterParaListagemDTO(Cliente cliente){
        ListarClientesDTO clienteDTO = new ListarClientesDTO();
//        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNome( cliente.getNome() );
        return clienteDTO;
    }

//    public Cliente cadastrarCliente(Cliente cl) {
//        return clienteRepository.save(cl);
//
//    }

    public Cliente cadastrarCliente(CadastrarClienteDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setNome( dto.getNome());
        cliente.setTelefone( dto.getTelefone());
        //usuario.setSenha(dto.getSenha());
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        cliente.setSenha(senhaCriptografada);

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }

        return clienteRepository.save(cliente);
    }

//    public Cliente cadastrarCliente(Cliente cl) {
//
//        if (clienteRepository.existsByEmail(cl.getEmail())) {
//            throw new EmailJaCadastradoException("E-mail já cadastrado");
//        }
//
//        return clienteRepository.save(cl);
//    }


    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);

    }
//    public Cliente buscarPorEmail(String email) {
//        return clienteRepository.findByEmail(email).orElse(null);
//}

    public ListarClientesDTO buscarPorEmail(String email) {
        Cliente c = clienteRepository.findByEmail(email).orElse(null);
        if (c == null) {
            return null;
        }
        ListarClientesDTO clienteDTO = new ListarClientesDTO();
        clienteDTO.setNome(c.getNome());
        clienteDTO.setEmail(c.getEmail());
        return clienteDTO;

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
