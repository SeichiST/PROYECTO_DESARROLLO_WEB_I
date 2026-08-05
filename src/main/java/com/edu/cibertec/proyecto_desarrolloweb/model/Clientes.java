package com.edu.cibertec.proyecto_desarrolloweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "Clientes")
@Entity
@Getter
@Setter
public class Clientes {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idcliente;

    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    private LocalDate fechanacimiento;
    private String sexo;
    private String correo;
    private String password;
    private String estado = "1";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_roles",
            joinColumns = @JoinColumn(name = "Idcliente"),
            inverseJoinColumns = @JoinColumn(name = "idrol")
    )
    private Set<Roles> roles;



}
