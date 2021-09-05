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
@Table(name = "Medico")
public class Medico implements Serializable {

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
    @Column(name = "estado")
    private String estado;

    @NonNull
    @Column(name = "id_usuario")
    private int idUsuario;
}
