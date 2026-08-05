package com.edu.cibertec.proyecto_desarrolloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}