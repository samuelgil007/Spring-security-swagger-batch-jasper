package com.konectaBack.konectaBack.Models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NonNull
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "nombre_rol")
    private String nombreRol;

    @NonNull
    @Column(name = "id_usuario")
    private int idusuario;
}
