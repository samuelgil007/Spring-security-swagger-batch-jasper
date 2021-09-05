package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.DTOMappers.UsuarioDTOMapper;
import com.konectaBack.konectaBack.DTOs.Responses.ErrorResponse;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.DTOs.UsuarioDTO;
import com.konectaBack.konectaBack.Models.Usuario;
import com.konectaBack.konectaBack.Repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly=true)
    public List<Usuario> retornarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Response crearUsuario(UsuarioDTO usuario){
        try{
            Usuario usuarioGuardado = usuarioRepository.save(
                    UsuarioDTOMapper.mapearUsuario(usuario)
            );
            return Response.builder()
                    .status(201)
                    .payload(usuarioGuardado)
                    .build();
        }catch (Exception e){
            return Response.builder()
                    .status(400)
                    .error(ErrorResponse.builder()
                            .message("Hubo un error en la creación del usuario")
                            .timestamp(new Date())
                            .build())
                    .build();
        }

    }

}
