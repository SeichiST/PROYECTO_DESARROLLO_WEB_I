package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Detalle")
@Entity
@Getter @Setter
public class Detalle {

    @EmbeddedId
    private DetallePK id = new DetallePK();

    @ManyToOne
    @MapsId("idventa")
    @JoinColumn(name = "idventa")
    private Ventas venta;

    @ManyToOne
    @MapsId("idjuegos")
    @JoinColumn(name = "idjuegos")
    private Juegos juego;

    private Integer cantidad;
    private Double precio;
    private String estado = "1";
}