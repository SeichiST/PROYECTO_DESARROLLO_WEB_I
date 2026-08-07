package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Juegos")
@Entity
@Getter @Setter
public class Juegos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idjuegos;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categorias categoria;

    private String descripcion;
    private Double precio;
    private String imagen;
    private Boolean activo;

}
