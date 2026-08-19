package com.edu.cibertec.proyecto_desarrolloweb.service;

import com.edu.cibertec.proyecto_desarrolloweb.dto.ClientesDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;
import com.edu.cibertec.proyecto_desarrolloweb.model.Roles;
import com.edu.cibertec.proyecto_desarrolloweb.repository.ClientesRepository;
import com.edu.cibertec.proyecto_desarrolloweb.repository.RolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClientesRepository clientesRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClientesRepository clientesRepository,
                          RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder) {
        this.clientesRepository = clientesRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Clientes> listarClientes() {
        return clientesRepository.findByEstado("1");
    }


    public ClientesDto buscarPorId(Integer id) {
        Clientes cliente = clientesRepository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }
        ClientesDto dto = new ClientesDto();
        dto.setIdcliente(cliente.getIdcliente());
        dto.setNombres(cliente.getNombres());
        dto.setApellidos(cliente.getApellidos());
        dto.setDni(cliente.getDni());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setFechanacimiento(cliente.getFechanacimiento());
        dto.setSexo(cliente.getSexo());
        dto.setCorreo(cliente.getCorreo());
        dto.setPassword(cliente.getPassword());
        dto.setEstado(cliente.getEstado());


        Set<Integer> roleIds = cliente.getRoles().stream()
                .map(Roles::getIdrol)
                .collect(Collectors.toSet());
        dto.setRoleIds(roleIds);

        return dto;
    }


    public void guardarCliente(ClientesDto dto) {

        if (dto.getRoleIds() == null || dto.getRoleIds().isEmpty()) {
            dto.setRoleIds(new HashSet<>(Collections.singletonList(2)));
        }

        Set<Roles> roles = dto.getRoleIds().stream()
                .map(rolesRepository::findById)
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .collect(Collectors.toSet());

        Clientes cliente = new Clientes();
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setDni(dto.getDni());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setFechanacimiento(dto.getFechanacimiento());
        cliente.setSexo(dto.getSexo());
        cliente.setCorreo(dto.getCorreo());
        cliente.setPassword(passwordEncoder.encode(dto.getPassword()));
        cliente.setEstado(dto.getEstado() != null ? dto.getEstado() : "1");
        cliente.setRoles(roles);

        clientesRepository.save(cliente);
    }


    public void actualizarCliente(ClientesDto dto) {

        String contra = dto.getPassword();
        if (contra != null && !contra.startsWith("$2a$")) {
            contra = passwordEncoder.encode(contra);
        }

        clientesRepository.updateCliente(
                dto.getNombres(),
                dto.getApellidos(),
                dto.getDni(),
                dto.getDireccion(),
                dto.getTelefono(),
                dto.getFechanacimiento(),
                dto.getSexo(),
                dto.getCorreo(),
                contra,
                dto.getEstado(),
                dto.getIdcliente()
        );


        Clientes cliente = clientesRepository.findById(dto.getIdcliente()).orElse(null);
        if (cliente != null && dto.getRoleIds() != null) {
            Set<Roles> nuevosRoles = dto.getRoleIds().stream()
                    .map(rolesRepository::findById)
                    .filter(opt -> opt.isPresent())
                    .map(opt -> opt.get())
                    .collect(Collectors.toSet());

            cliente.getRoles().clear();
            cliente.getRoles().addAll(nuevosRoles);
            clientesRepository.save(cliente);
        }
    }


    public void eliminarCliente(Integer id) {
        clientesRepository.deleteCliente(id);
    }
}
