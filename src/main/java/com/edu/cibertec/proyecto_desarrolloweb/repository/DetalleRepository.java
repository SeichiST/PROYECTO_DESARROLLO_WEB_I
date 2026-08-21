package com.edu.cibertec.proyecto_desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Detalle;
import com.edu.cibertec.proyecto_desarrolloweb.model.DetallePK;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, DetallePK> {
    @Query("SELECT d FROM Detalle d WHERE d.venta.idventa = :idVenta")
    List<Detalle> findByIdVenta(@Param("idVenta") Integer idVenta);
}