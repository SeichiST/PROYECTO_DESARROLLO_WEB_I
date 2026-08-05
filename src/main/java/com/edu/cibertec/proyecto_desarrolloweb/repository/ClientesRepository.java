package com.edu.cibertec.proyecto_desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Clientes findByCorreo(String correo);
}