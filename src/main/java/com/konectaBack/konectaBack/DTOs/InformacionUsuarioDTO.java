package com.konectaBack.konectaBack.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacionUsuarioDTO {

    @NotNull(message = "El rol no puede ser null")
    @NotBlank(message = "El rol no puede ser vacío")
    private String rol;

    private String nombre;

    private String apellido;

    private int id;

    @NotNull(message = "El idUsuario no puede ser null")
    @NotBlank(message = "El idUsuario no puede ser vacío")
    private int idUsuario;

    private String token;
}
