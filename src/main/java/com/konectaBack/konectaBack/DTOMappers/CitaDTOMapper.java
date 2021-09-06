package com.konectaBack.konectaBack.DTOMappers;

import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Models.Medico;

public class CitaDTOMapper {
    public static Cita mapearCita(CitaDTO cita, Medico medico){

        return Cita.builder()
                .id(cita.getId())
                .detalle(cita.getDetalle())
                .fechaInicio(cita.getFechaInicio())
                .fechaFin(cita.getFechaFin())
                .tipoAtencion(cita.getTipoAtencion())
                .idMedico(medico)
                .idPaciente(cita.getIdPaciente())
                .build();
    }
}
