package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;
import com.edu.cibertec.proyecto_desarrolloweb.service.CategoriasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoriasController {
    private final CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<List<Categorias>> getAllCategorias(){
        return new ResponseEntity<>(
                categoriasService.listarCategorias(),
                HttpStatus.OK);
    }
}
