package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Roles")
@Entity
@Getter @Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrol;

    private String nombre;
}
