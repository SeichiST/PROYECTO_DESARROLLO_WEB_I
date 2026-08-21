package com.edu.cibertec.proyecto_desarrolloweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleVentaDto {
    private Integer idJuego;
    private String descripcionJuego;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;
}