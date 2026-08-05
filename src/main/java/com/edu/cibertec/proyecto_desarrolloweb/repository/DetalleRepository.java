package com.edu.cibertec.proyecto_desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Detalle;
import com.edu.cibertec.proyecto_desarrolloweb.model.DetallePK;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, DetallePK> {
}