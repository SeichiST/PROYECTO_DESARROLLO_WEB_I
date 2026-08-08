package com.edu.cibertec.proyecto_desarrolloweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.edu.cibertec.proyecto_desarrolloweb.model.Clientes;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Clientes findByCorreo(String correo);

    // Actualizar campos del cliente (excepto los roles)
    @Transactional
    @Modifying
    @Query(value = """
        update Clientes set nombres=:nombres, apellidos=:apellidos,
            dni=:dni, direccion=:direccion, telefono=:telefono,
            fechanacimiento=:fechanacimiento, sexo=:sexo, correo=:correo,
            password=:password, estado=:estado
        where idcliente=:idcliente
        """, nativeQuery = true)
    void updateCliente(@Param("nombres") String nombres,
                       @Param("apellidos") String apellidos,
                       @Param("dni") String dni,
                       @Param("direccion") String direccion,
                       @Param("telefono") String telefono,
                       @Param("fechanacimiento") java.time.LocalDate fechanacimiento,
                       @Param("sexo") String sexo,
                       @Param("correo") String correo,
                       @Param("password") String password,
                       @Param("estado") String estado,
                       @Param("idcliente") Integer idcliente);

    // Eliminación lógica (cambiar estado a '0')
    @Transactional
    @Modifying
    @Query(value = """
        update Clientes set estado='0' where idcliente=:idcliente
        """, nativeQuery = true)
    void deleteCliente(@Param("idcliente") Integer idcliente);

    // Listar solo clientes activos
    List<Clientes> findByEstado(String estado);

}