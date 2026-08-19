package com.edu.cibertec.proyecto_desarrolloweb.dto;
import lombok.Data;

@Data
public class UsuarioSesionDto {
    private Integer idcliente;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String rol;
}