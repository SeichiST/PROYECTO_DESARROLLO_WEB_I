package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.LoginDto;
import com.edu.cibertec.proyecto_desarrolloweb.dto.UsuarioSesionDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;
import com.edu.cibertec.proyecto_desarrolloweb.repository.ClientesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final ClientesRepository clientesRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(ClientesRepository clientesRepository, PasswordEncoder passwordEncoder) {
        this.clientesRepository = clientesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Map<String, String> response = new HashMap<>();

        Clientes cliente = clientesRepository.findByCorreo(loginDto.getCorreo());

        if (cliente == null || !"1".equals(cliente.getEstado())) {
            response.put("mensaje", "Usuario no encontrado o inactivo");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), cliente.getPassword())) {
            response.put("mensaje", "Contraseña incorrecta");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        String rol = cliente.getRoles().isEmpty() ? "ROLE_CLIENTE" :
                cliente.getRoles().iterator().next().getNombre();

        UsuarioSesionDto sesion = new UsuarioSesionDto();
        sesion.setIdcliente(cliente.getIdcliente());
        sesion.setNombres(cliente.getNombres());
        sesion.setApellidos(cliente.getApellidos());
        sesion.setCorreo(cliente.getCorreo());
        sesion.setTelefono(cliente.getTelefono());
        sesion.setRol(rol);

        return ResponseEntity.ok(sesion);
    }
}
