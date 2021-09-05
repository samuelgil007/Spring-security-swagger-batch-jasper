package com.konectaBack.konectaBack.DTOMappers;

import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.Models.Cita;

public class CitaDTOMapper {
    public static Cita mapearCita(CitaDTO cita){

        return Cita.builder()
                .id(cita.getId())
                .detalle(cita.getDetalle())
                .fechaInicio(cita.getFechaInicio())
                .fechaFin(cita.getFechaFin())
                .tipoAtencion(cita.getTipoAtencion())
                .idMedico(cita.getIdMedico())
                .idPaciente(cita.getIdPaciente())
                .build();
    }
}
