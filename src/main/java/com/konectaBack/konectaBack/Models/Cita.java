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
@Table(name = "Cita")
public class Cita  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "id")
    private int id;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "id_medico")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "Cita")
    private Medico idMedico;

    @Column(name = "id_paciente")
    private  int idPaciente;

    @Column(name = "tipo_atencion")
    private String tipoAtencion;

    @Column(name = "detalle")
    private String detalle;

}
