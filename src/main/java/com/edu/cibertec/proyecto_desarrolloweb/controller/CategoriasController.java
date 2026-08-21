package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.CategoriasDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;
import com.edu.cibertec.proyecto_desarrolloweb.service.CategoriasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoriasController {

    private final CategoriasService categoriasService;

    // GET - Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<Categorias>> getAllCategorias() {
        return new ResponseEntity<>(
                categoriasService.listarCategorias(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDto> getCategoriaById(@PathVariable String id) {
        CategoriasDto dto = categoriasService.buscarPorId(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // POST - Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Map<String, String>> createCategoria(@RequestBody CategoriasDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            categoriasService.guardarCategoria(dto);
            response.put("mensaje", "Categoría creada correctamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al crear la categoría: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH - Actualizar una categoría existente
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarCategoria(
            @PathVariable String id,
            @RequestBody CategoriasDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            dto.setIdcategoria(id);
            categoriasService.actualizarCategoria(dto);
            response.put("mensaje", "Categoría actualizada correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar la categoría: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}