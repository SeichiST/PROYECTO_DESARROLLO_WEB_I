package com.edu.cibertec.proyecto_desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, String> {
}