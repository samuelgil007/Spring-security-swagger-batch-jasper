package com.konectaBack.konectaBack.DTOMappers;

import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.DTOs.UsuarioDTO;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Models.Usuario;
import org.springframework.web.bind.annotation.RestController;


public class UsuarioDTOMapper {
    public static Usuario mapearUsuario(UsuarioDTO usuario){

        return Usuario.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .estado(usuario.getEstado())
                .build();
    }
}
