package br.com.pdv.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente", schema = "pdv")
public class Cliente implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Id
    @Column(name = "id_cliente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private Integer cpf;

    @Column(name = "telefone", nullable = false, length = Integer.MAX_VALUE)
    private String telefone;

    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = Integer.MAX_VALUE)
    private String senha;





}