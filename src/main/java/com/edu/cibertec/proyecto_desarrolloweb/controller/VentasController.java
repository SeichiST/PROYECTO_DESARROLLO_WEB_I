package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.VentaDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Ventas;
import com.edu.cibertec.proyecto_desarrolloweb.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarVenta(@RequestBody VentaDto ventaDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Ventas nuevaVenta = ventasService.registrarVenta(ventaDto);

            response.put("mensaje", "Venta registrada con éxito");
            response.put("idVenta", nuevaVenta.getIdventa());

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.put("mensaje", "Error al registrar la venta: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}