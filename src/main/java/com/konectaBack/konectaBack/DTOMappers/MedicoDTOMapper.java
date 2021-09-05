package com.konectaBack.konectaBack.DTOMappers;

import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Models.Usuario;
import com.konectaBack.konectaBack.Repositories.UsuarioRepository;

public class MedicoDTOMapper {

    public static Medico mapearMedico(MedicoDTO medico, int usuarioId){

        return Medico.builder()
                .id(medico.getId())
                .estado(medico.getEstado())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .idUsuario(usuarioId)
                .build();
    }

    public static MedicoDTO mapearMedicoDTO(Medico medico, Usuario usuario){

        return MedicoDTO.builder()
                .id(medico.getId())
                .estado(medico.getEstado())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .usuario(usuario)
                .build();
    }
}
