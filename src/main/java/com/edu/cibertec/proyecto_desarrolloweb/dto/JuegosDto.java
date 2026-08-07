package com.edu.cibertec.proyecto_desarrolloweb.dto;

import lombok.Data;

@Data
public class JuegosDto {

    private Integer idjuegos;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String idcategoria;
    private Boolean activo;

}
