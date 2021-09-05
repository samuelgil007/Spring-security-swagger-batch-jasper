package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.DTOMappers.MedicoDTOMapper;
import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.DTOs.Responses.ErrorResponse;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Models.Rol;
import com.konectaBack.konectaBack.Models.Usuario;
import com.konectaBack.konectaBack.Repositories.MedicoRepository;
import com.konectaBack.konectaBack.Repositories.RolRepository;
import com.konectaBack.konectaBack.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MedicoService {

    private MedicoRepository medicoRepository;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    public MedicoService(MedicoRepository medicoRepository, UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.medicoRepository = medicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Transactional
    public Response crearMedico(MedicoDTO medico){
        //Si hay un medico con mismo nombre y apellido se le crea un correo con numero despues
        List<Medico> medicoIgual = medicoRepository.findByNombreAndApellido(
                medico.getNombre(), medico.getApellido());
        try{
            Usuario usuario = usuarioRepository.save(Usuario.builder()
            .estado("Activado")
            .email(medico.getNombre()+medico.getApellido()+
                    (medicoIgual.size() > 0? medicoIgual.size()+1: "")+"@correo.com")
            .password("1234").build());

            Rol rol = rolRepository.save(Rol.builder()
            .nombreRol("medico")
            .idusuario(usuario.getId())
            .build());

            Medico medicoNuevo = medicoRepository.save(
                    MedicoDTOMapper.mapearMedico(medico,usuario.getId()));

            return Response.builder()
                    .status(201)
                    .payload(MedicoDTOMapper.mapearMedicoDTO(
                            medicoNuevo,usuario))
                    .build();
        }catch (Exception e){
            System.out.println("error:  = " + e);
            return Response.builder()
                    .status(400)
                    .error(ErrorResponse.builder()
                            .message("Hubo un error en la creación del medico")
                            .timestamp(new Date())
                            .build())
                    .build();
        }

    }

    @Transactional
    public Response borrarMedico(int id){
        Medico medicoExiste = medicoRepository.findById(id);
        if(medicoExiste != null){
            Medico medico = medicoRepository.findById(id);
            medicoRepository.deleteById(id);
            rolRepository.deleteByIdusuario(medico.getIdUsuario());
            usuarioRepository.deleteById(medico.getIdUsuario());

            return Response.builder()
                    .status(200)
                    .payload("Medico eliminado con exito")
                    .build();
        }return Response.builder()
                .status(404)
                .error(ErrorResponse.builder()
                        .message("El medico con el id "+ id + " no existe")
                        .timestamp(new Date())
                        .build())
                .build();
    }

    @Transactional
    public Response desactivarMedico(int id){
        Medico medicoExiste = medicoRepository.findById(id);
        if(medicoExiste != null){
            Medico medico = medicoRepository.findById(id);
            medico.setEstado("inactivo");
            medicoRepository.save(medico);

            Usuario usuario = usuarioRepository.findById(medico.getIdUsuario());
            usuario.setEstado("Desactivado");
            usuarioRepository.save(usuario);


            return Response.builder()
                    .status(200)
                    .payload("Medico desactivado con exito")
                    .build();
        }return Response.builder()
                .status(404)
                .error(ErrorResponse.builder()
                        .message("El medico con el id "+ id + " no existe")
                        .timestamp(new Date())
                        .build())
                .build();
    }

    @Transactional
    public Response activarMedico(int id){
        Medico medicoExiste = medicoRepository.findById(id);
        if(medicoExiste != null){
            Medico medico = medicoRepository.findById(id);
            medico.setEstado("Activo");
            medicoRepository.save(medico);
            Usuario usuario = usuarioRepository.findById(medico.getIdUsuario());
            usuario.setEstado("Activado");
            usuarioRepository.save(usuario);

            return Response.builder()
                    .status(200)
                    .payload("Medico activado con exito")
                    .build();
        }
            return Response.builder()
                    .status(404)
                    .error(ErrorResponse.builder()
                            .message("El medico con el id "+ id + " no existe")
                            .timestamp(new Date())
                            .build())
                    .build();

    }

    @Transactional(readOnly=true)
    public List<Medico> retornarMedicos(){
        return medicoRepository.findAll();
    }
}
