package com.konectaBack.konectaBack.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private int id;

    @NotNull(message = "El email no puede ser null")
    @NotBlank(message = "El email no puede ser vacío")
    @Email(message = "El formato del correo es incorrecto")
    private String email;

    @NotNull(message = "El password no puede ser null")
    @NotBlank(message = "El password no puede ser vacío")
    private String password;

    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede ser vacío")
    private String estado;
}
