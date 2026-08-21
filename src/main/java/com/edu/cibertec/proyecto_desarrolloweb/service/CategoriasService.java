package com.edu.cibertec.proyecto_desarrolloweb.service;

import com.edu.cibertec.proyecto_desarrolloweb.dto.CategoriasDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;
import com.edu.cibertec.proyecto_desarrolloweb.repository.CategoriasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public CategoriasDto buscarPorId(String id) {
        Categorias categoria = categoriasRepository.findById(id).orElse(null);
        if (categoria == null) {
            return null;
        }
        CategoriasDto dto = new CategoriasDto();
        dto.setIdcategoria(categoria.getIdcategoria());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }

    @Transactional
    public void guardarCategoria(CategoriasDto dto) {
        if (categoriasRepository.existsById(dto.getIdcategoria())) {
            throw new RuntimeException("La categoría con ID " + dto.getIdcategoria() + " ya existe");
        }

        Categorias categoria = new Categorias();
        categoria.setIdcategoria(dto.getIdcategoria());
        categoria.setDescripcion(dto.getDescripcion());

        categoriasRepository.save(categoria);
    }

    @Transactional
    public void actualizarCategoria(CategoriasDto dto) {
        Categorias categoria = categoriasRepository.findById(dto.getIdcategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + dto.getIdcategoria()));

        categoria.setDescripcion(dto.getDescripcion());

        categoriasRepository.save(categoria);
    }
}