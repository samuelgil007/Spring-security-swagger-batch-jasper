package com.konectaBack.konectaBack.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Paciente")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "apellido")
    private String apellido;

    @NonNull
    @Column(name = "telefono")
    private String telefono;

    @NonNull
    @Column(name = "direccion")
    private String direccion;

    @NonNull
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @NonNull
    @Column(name = "fecha_ultimoIngreso")
    private Date fechaUltimoIngreso;

    @NonNull
    @Column(name = "cedula", unique=true)
    private String cedula;

    @NonNull
    @Column(name = "id_usuario")
    private int idUsuario;

}
