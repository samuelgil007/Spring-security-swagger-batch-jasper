package com.konectaBack.konectaBack.DTOMappers;

import com.konectaBack.konectaBack.DTOs.PacienteDTO;
import com.konectaBack.konectaBack.Models.Paciente;
import com.konectaBack.konectaBack.Models.Usuario;

public class PacienteDTOMapper {
    public static Paciente mapearPaciente(PacienteDTO paciente, int idUsuario){

        return Paciente.builder()
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .cedula(paciente.getCedula())
                .direccion(paciente.getDireccion())
                .telefono(paciente.getTelefono())
                .fechaCreacion(paciente.getFechaCreacion())
                .fechaUltimoIngreso(paciente.getFechaUltimoIngreso())
                .idUsuario(idUsuario)
                .build();
    }
    public static PacienteDTO mapearPacienteDTO(Paciente paciente, Usuario usuario){

        return PacienteDTO.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .cedula(paciente.getCedula())
                .direccion(paciente.getDireccion())
                .telefono(paciente.getTelefono())
                .fechaCreacion(paciente.getFechaCreacion())
                .fechaUltimoIngreso(paciente.getFechaUltimoIngreso())
                .idUsuario(usuario.getId())
                .usuario(usuario)
                .build();
    }

}
