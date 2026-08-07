package com.edu.cibertec.proyecto_desarrolloweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Juegos;

import java.util.List;

@Repository
public interface JuegosRepository extends JpaRepository<Juegos, Integer> {

    @Transactional
    @Modifying
    @Query(value = """
        update Juegos set descripcion=:descripcion, precio=:precio,
            imagen=:imagen, idcategoria=:idcategoria, activo=:activo
        where idjuegos=:idjuegos
        """, nativeQuery = true)
    void updateJuego(@Param("descripcion") String descripcion,
                     @Param("precio") Double precio,
                     @Param("imagen") String imagen,
                     @Param("idcategoria") String idcategoria,
                     @Param("activo") boolean activo,
                     @Param("idjuegos") Integer idjuegos);
    @Transactional
    @Modifying
    @Query(value = """
    update Juegos set activo=:activo where idjuegos=:idjuegos
    """, nativeQuery = true)
    void deleteJuego(@Param("activo") boolean activo,
                     @Param("idjuegos") Integer idjuegos);

    List<Juegos> findByActivoTrue();
}