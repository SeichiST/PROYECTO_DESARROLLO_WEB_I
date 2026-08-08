package com.edu.cibertec.proyecto_desarrolloweb.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ClientesDto {

    private Integer idcliente;
    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private LocalDate fechanacimiento;
    private String sexo;
    private String correo;
    private String password;
    private String estado;
    private Set<Integer> roleIds;

}
