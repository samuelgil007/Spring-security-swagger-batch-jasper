package com.konectaBack.konectaBack.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.konectaBack.konectaBack.Models.Usuario;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicoDTO {

    private int id;

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotNull(message = "El apellido no puede ser null")
    @NotBlank(message = "El apellido no puede ser vacío")
    private String apellido;

    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede ser vacío")
    private String estado;

    private Usuario usuario;
}
