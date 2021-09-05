package com.konectaBack.konectaBack.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.konectaBack.konectaBack.Models.Usuario;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacienteDTO {

    private int id;

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotNull(message = "El apellido no puede ser null")
    @NotBlank(message = "El apellido no puede ser vacío")
    private String apellido;

    @NotNull(message = "El telefono no puede ser null")
    @NotBlank(message = "El telefono no puede ser vacío")
    private String telefono;

    @NotNull(message = "El direccion no puede ser null")
    @NotBlank(message = "El direccion no puede ser vacía")
    private String direccion;

    @NotNull(message = "La cedula no puede ser null")
    @NotBlank(message = "La cedula no puede ser vacía")
    private String cedula;

    @NotNull(message = "El fecha de creacion no puede ser null")
    @DateTimeFormat
    private Date fechaCreacion;

    @NotNull(message = "El fecha de creacion no puede ser null")
    @DateTimeFormat
    private Date fechaUltimoIngreso;

    private Usuario usuario;

    private int idUsuario;
}
