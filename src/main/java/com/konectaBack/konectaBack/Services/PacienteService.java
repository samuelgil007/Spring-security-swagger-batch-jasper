package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.DTOMappers.MedicoDTOMapper;
import com.konectaBack.konectaBack.DTOMappers.PacienteDTOMapper;
import com.konectaBack.konectaBack.DTOs.PacienteDTO;
import com.konectaBack.konectaBack.DTOs.Responses.ErrorResponse;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.*;
import com.konectaBack.konectaBack.Repositories.PacienteRepository;
import com.konectaBack.konectaBack.Repositories.RolRepository;
import com.konectaBack.konectaBack.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly=true)
    public List<Paciente> retornarPacientes(){
        return pacienteRepository.findAll();
    }

    @Transactional
    public Response crearPaciente(PacienteDTO paciente){
        Paciente pacienteIgual = pacienteRepository.findByCedula(paciente.getCedula());
        if(pacienteIgual == null ){

            Usuario usuario = usuarioRepository.save(Usuario.builder()
                    .estado("Activado")
                    .email(paciente.getCedula()+"@correo.com")
                    .password("1234").build());

            Rol rol = rolRepository.save(Rol.builder()
                    .nombreRol("paciente")
                    .idusuario(usuario.getId())
                    .build());

            Paciente pacienteGuardado = pacienteRepository.save(
                    PacienteDTOMapper.mapearPaciente(paciente, usuario.getId()));

            return Response.builder()
                    .status(201)
                    .payload(PacienteDTOMapper.mapearPacienteDTO(
                            pacienteGuardado,usuario))
                    .build();
        }
            return Response.builder()
                    .status(409)
                    .error(ErrorResponse.builder()
                            .message("Ya existe un paciente con esa cedula")
                            .timestamp(new Date())
                            .build())
                    .build();


    }
}
