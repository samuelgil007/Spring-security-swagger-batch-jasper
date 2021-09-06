package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.DTOMappers.CitaDTOMapper;
import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.Responses.ErrorResponse;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Repositories.CitaRepository;
import com.konectaBack.konectaBack.Repositories.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CitaService {

    private CitaRepository citaRepository;
    private MedicoRepository medicoRepository;

    public CitaService(CitaRepository citaRepository, MedicoRepository medicoRepository) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    public Response crearCita(CitaDTO cita){
        if (puedeAgendarCita(cita,false)) {
            try {
                Cita citaGuardada = citaRepository.save(
                        CitaDTOMapper.mapearCita(cita, medicoRepository.findById(cita.getIdMedico()))
                );
                return Response.builder()
                        .status(201)
                        .payload(citaGuardada)
                        .build();
            } catch (Exception e) {
                return Response.builder()
                        .status(400)
                        .error(ErrorResponse.builder()
                                .message("Hubo un error en la creación de la cita")
                                .timestamp(new Date())
                                .build())
                        .build();
            }
        }
        return Response.builder()
                .status(409)
                .error(ErrorResponse.builder()
                        .message("Ya existe una cita a esa hora con ese medico")
                        .timestamp(new Date())
                        .build())
                .build();
    }

    @Transactional(readOnly=true)
    public List<Cita> retornarCitas(){
        return citaRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Cita> retornarCitasPorMedico(int id){
        return citaRepository.findAllByIdMedico(id);
    }

    @Transactional(readOnly=true)
    public List<Cita> retornarCitasPorPaciente(int id){
        return citaRepository.findAllByIdPaciente(id);
    }

    @Transactional
    public Response borrarCita(int id){
        Cita citaExiste = citaRepository.findById(id);
        if (citaExiste != null) {
        citaRepository.deleteById(id);
            return Response.builder()
                    .status(200)
                    .payload("Cita eiminada con exito")
                    .build();
        }
        return Response.builder()
                .status(404)
                .error(ErrorResponse.builder()
                        .message("La cita con el id "+ id + " no existe")
                        .timestamp(new Date())
                        .build())
                .build();
    }

    @Transactional
    public Response modificarCita(CitaDTO cita, int id) {
        cita.setId(id);
        if (puedeAgendarCita(cita,true)) {
                    citaRepository.save(CitaDTOMapper.mapearCita(cita, medicoRepository.findById(cita.getIdMedico())));
                    return Response.builder()
                            .status(200)
                            .payload(cita)
                            .build();
            }
            return Response.builder()
                    .status(409)
                    .error(ErrorResponse.builder()
                            .message("Ya existe una cita a esa hora con ese medico")
                            .timestamp(new Date())
                            .build())
                    .build();
        }

    public Boolean puedeAgendarCita(CitaDTO cita, Boolean esEditada) {

        Cita citaAGuardar = esEditada? citaRepository.findById(cita.getId()):null;
        Cita citaAdentroFechas = citaRepository.findByFechaInicioBeforeAndFechaFinAfterAndIdMedico
                (cita.getFechaInicio(), cita.getFechaFin(), cita.getIdMedico());
        Cita citaMismaHora = citaRepository.findByFechaInicioAndFechaFinAndIdMedico
                (cita.getFechaInicio(), cita.getFechaFin(), cita.getIdMedico());
        if (citaAdentroFechas == null && (citaMismaHora == citaAGuardar || citaMismaHora == null)) {
            return true;
        }
        return false;
    }

}
