package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.MensajeDto;
import com.edu.cibertec.proyecto_desarrolloweb.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mensaje")
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping
    public List<MensajeDto> listarMensaje(){
        return mensajeService.listarMensajes();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registrarMensaje(@RequestBody MensajeDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            mensajeService.guardarMensaje(dto);
            response.put("mensaje", "Mensaje registrado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("mensaje", "Error al registrar mensaje: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
