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
public class LoginDTO {


    @NotNull(message = "El usuario no puede ser null")
    @NotBlank(message = "El usuario no puede ser vacío")
    @Email(message = "Debe ser en formato de email")
    public String email;

    @NotNull(message = "La contraseña no puede ser null")
    @NotBlank(message = "La contraseña no puede ser vacía")
    public String password;
}
