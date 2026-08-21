package com.edu.cibertec.proyecto_desarrolloweb.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaDetalleResponseDto {
    private Integer idVenta;
    private Integer idCliente;
    private String nombresCliente;
    private String apellidosCliente;
    private LocalDateTime fechaVenta;
    private Double montoTotal;
    private String estado;
    private List<DetalleVentaDto> detalles;
}
