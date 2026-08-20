package com.edu.cibertec.proyecto_desarrolloweb.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDto {
    private Integer idCliente;
    private Double montoTotal;
    private List<DetalleVentaDto> detalles;
}