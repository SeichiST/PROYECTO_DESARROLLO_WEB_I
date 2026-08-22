package com.edu.cibertec.proyecto_desarrolloweb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class MensajeDto {
    private Integer idmensaje;
    private Integer idcliente;
    private LocalDateTime fechaenvio;
    private String nombres;
    private String correo;
    private String telefono;
    private String textomensaje;
    private String estado;
}
