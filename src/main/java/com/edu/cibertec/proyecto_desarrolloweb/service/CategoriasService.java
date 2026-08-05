package com.edu.cibertec.proyecto_desarrolloweb.service;

import org.springframework.stereotype.Service;
import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;
import com.edu.cibertec.proyecto_desarrolloweb.repository.CategoriasRepository;
import java.util.List;

@Service
public class CategoriasService {

    private final CategoriasRepository categoriasRepository;

    public CategoriasService(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    public List<Categorias> listarCategorias() {
        return categoriasRepository.findAll();
    }
}