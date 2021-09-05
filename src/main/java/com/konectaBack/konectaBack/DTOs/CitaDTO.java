package com.konectaBack.konectaBack.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitaDTO {

    private int id;

    @NotNull(message = "la fecha de inicio no puede ser null")
    @DateTimeFormat
    private Date fechaInicio;

    @NotNull(message = "la fecha  de fin no puede ser null")
    @DateTimeFormat
    private Date fechaFin;

    @NotNull(message = "El id del medico no puede ser null")
    private int idMedico;

    @NotNull(message = "El id del paciente no puede ser null")
    private  int idPaciente;

    @NotNull(message = "El tipo de atencion no puede ser null")
    @NotBlank(message = "El tipo de atencion no puede ser vacío")
    private String tipoAtencion;

    private String detalle;
}
