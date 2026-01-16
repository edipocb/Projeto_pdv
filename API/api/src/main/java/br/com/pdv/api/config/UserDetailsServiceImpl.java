package br.com.pdv.api.config;


import br.com.pdv.api.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public UserDetailsServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) clienteRepository.findByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado!"));
    }
}
