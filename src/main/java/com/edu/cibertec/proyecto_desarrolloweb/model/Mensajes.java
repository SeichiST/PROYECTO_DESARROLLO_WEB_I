package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "Mensajes")
@Entity
@Getter @Setter
public class Mensajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmensaje;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Clientes cliente;

    private String textomensaje;
    private LocalDateTime fechaenvio;
    private String estado = "1";

    @PrePersist
    public void prePersist() {
        if (this.fechaenvio == null) {
            this.fechaenvio = LocalDateTime.now();
        }
    }
}