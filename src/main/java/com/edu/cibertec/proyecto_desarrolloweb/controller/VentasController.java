package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.VentaDetalleResponseDto;
import com.edu.cibertec.proyecto_desarrolloweb.dto.VentaDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Ventas;
import com.edu.cibertec.proyecto_desarrolloweb.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    // GET - Listar todas las ventas
    @GetMapping
    public ResponseEntity<List<Ventas>> getAllVentas() {
        return new ResponseEntity<>(
                ventasService.listarVentas(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDetalleResponseDto> getVentaDetalle(@PathVariable Integer id) {
        try {
            VentaDetalleResponseDto detalle = ventasService.obtenerVentaDetalle(id);
            return new ResponseEntity<>(detalle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Registrar una nueva venta
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