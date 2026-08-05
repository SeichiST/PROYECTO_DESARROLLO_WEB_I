package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Ventas")
@Entity
@Getter @Setter
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idventa;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Clientes cliente;

    private LocalDateTime fechaventa;
    private Double montototal;
    private String estado = "1";

    @PrePersist
    public void prePersist() {
        if (this.fechaventa == null) {
            this.fechaventa = LocalDateTime.now();
        }
    }
}