package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Categorias")
@Getter @Setter
public class Categorias {

    @Id
    private String idcategoria;

    private String descripcion;

}
