package com.edu.cibertec.proyecto_desarrolloweb.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;
import com.edu.cibertec.proyecto_desarrolloweb.model.Roles;
import com.edu.cibertec.proyecto_desarrolloweb.repository.ClientesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    private final ClientesRepository clientesRepository;

    public DetalleUsuarioService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clientes cliente = clientesRepository.findByCorreo(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        boolean activo = "1".equals(cliente.getEstado());

        return new User(
                cliente.getCorreo(),
                cliente.getPassword(),
                activo, true, true, true,
                getRolUsuario(cliente.getRoles())
        );
    }

    private List<GrantedAuthority> getRolUsuario(Set<Roles> roles) {
        if (roles == null) return Collections.emptyList();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles r : roles) {
            authorities.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        return authorities;
    }
}