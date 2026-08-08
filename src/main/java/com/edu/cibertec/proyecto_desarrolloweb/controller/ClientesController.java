package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.ClientesDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;
import com.edu.cibertec.proyecto_desarrolloweb.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
public class ClientesController {

    private final ClienteService clientesService;

    // GET - Listar todos los clientes activos
    @GetMapping
    public ResponseEntity<List<Clientes>> getAllClientes() {
        return new ResponseEntity<>(
                clientesService.listarClientes(),
                HttpStatus.OK
        );
    }

    // GET - Obtener un cliente por ID (devuelve DTO con roles)
    @GetMapping("/{id}")
    public ResponseEntity<ClientesDto> getClienteById(@PathVariable Integer id) {
        ClientesDto dto = clientesService.buscarPorId(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // POST - Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Map<String, String>> createCliente(@RequestBody ClientesDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            clientesService.guardarCliente(dto);
            response.put("mensaje", "Cliente creado correctamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al registrar el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH - Actualizar un cliente existente
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarCliente(
            @PathVariable Integer id,
            @RequestBody ClientesDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            dto.setIdcliente(id);
            clientesService.actualizarCliente(dto);
            response.put("mensaje", "Cliente actualizado correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al actualizar el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE - Eliminación lógica (desactiva el cliente)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCliente(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            clientesService.eliminarCliente(id);
            response.put("mensaje", "Cliente eliminado correctamente (desactivado)");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al eliminar el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
