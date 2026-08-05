package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class DetallePK implements Serializable {

    private Integer idventa;
    private Integer idjuegos;
}