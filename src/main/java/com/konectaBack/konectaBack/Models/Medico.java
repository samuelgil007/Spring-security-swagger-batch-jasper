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

    public Medico(int id){
        this.id = id;
    }

    public Medico(@NonNull String nombre, @NonNull String apellido, @NonNull String estado, @NonNull int idUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

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
