package br.com.pdv.api.controller;

import br.com.pdv.api.dto.UsuarioDTO.ListarClientesDTO;
import br.com.pdv.api.dto.UsuarioDTO.LoginDTO;
import br.com.pdv.api.dto.LoginRequest;
import br.com.pdv.api.model.Cliente;
import br.com.pdv.api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final ClienteService clienteService;


    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, ClienteService clienteService) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.clienteService = clienteService;
    }

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        ListarClientesDTO cliente = clienteService.buscarPorEmail(loginRequest.getEmail());

        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());

        Authentication auth = authenticationManager.authenticate(authToken);

        Instant now = Instant.now();
        long validade = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("urbanswift-api") // Quem emitiu o token.
                .issuedAt(now) // Quando foi emitido.
                .expiresAt(now.plusSeconds(validade)) // Quando expira.
                .subject(auth.getName()) // A quem o token pertence (o email do usuário).
                .claim("roles", auth.getAuthorities()) // Informações extras, como os perfis do usuário.
                .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        LoginDTO login = new LoginDTO();
        login.setToken(token);
        login.setCliente(cliente);
        // 8. Retorna o token gerado para o cliente com um status 200 OK.
        // return ResponseEntity.ok(login);
        return ResponseEntity.ok(login);
    }
}
