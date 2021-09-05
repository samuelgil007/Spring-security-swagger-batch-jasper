package com.konectaBack.konectaBack.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konectaBack.konectaBack.DTOs.InformacionUsuarioDTO;
import com.konectaBack.konectaBack.DTOs.LoginDTO;
import com.konectaBack.konectaBack.DTOs.Responses.ErrorResponse;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Models.Paciente;
import com.konectaBack.konectaBack.Models.Rol;
import com.konectaBack.konectaBack.Models.Usuario;
import com.konectaBack.konectaBack.Repositories.MedicoRepository;
import com.konectaBack.konectaBack.Repositories.PacienteRepository;
import com.konectaBack.konectaBack.Repositories.RolRepository;
import com.konectaBack.konectaBack.Repositories.UsuarioRepository;
import com.konectaBack.konectaBack.utils.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;

@Service
public class LoginService implements UserDetailsService {

        private PacienteRepository pacienteRepository;
        private UsuarioRepository usuarioRepository;
        private RolRepository rolRepository;
        private MedicoRepository medicoRepository;

    public LoginService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository,
                        RolRepository rolRepository, MedicoRepository medicoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "foo", new ArrayList<>());
    }

    @Transactional
        public Response realizarLogin(LoginDTO login){
            try{
                Usuario usuarioExistente = usuarioRepository
                        .findByEmailAndPassword(login.getEmail(),login.getPassword());

                if(usuarioExistente.getEstado().equals("Activado")) {

                    Rol rol = rolRepository.findByIdusuario(usuarioExistente.getId());
                    String token = JwtUtils.generarToken(loadUserByUsername(usuarioExistente.getEmail()));

                    InformacionUsuarioDTO informacion = InformacionUsuarioDTO.builder()
                            .rol(rol.getNombreRol()).idUsuario(usuarioExistente.getId())
                            .token(token)
                            .build();

                    if (rol.getNombreRol().equals("paciente")) {
                        Paciente paciente = pacienteRepository.findByIdUsuario(usuarioExistente.getId());
                        informacion.setApellido(paciente.getApellido());
                        informacion.setNombre(paciente.getNombre());
                        informacion.setId(paciente.getId());
                        paciente.setFechaUltimoIngreso(new Date());
                        pacienteRepository.save(paciente);

                    } else if (rol.getNombreRol().equals("medico")) {
                        Medico medico = medicoRepository.findByIdUsuario(usuarioExistente.getId());
                        informacion.setApellido(medico.getApellido());
                        informacion.setNombre(medico.getNombre());
                        informacion.setId(medico.getId());
                    }
                    return Response.builder()
                            .status(200)
                            .payload(informacion)
                            .build();
                }
                return Response.builder()
                        .status(401)
                        .error(ErrorResponse.builder()
                                .message("Error, el usuario esta desactivado")
                                .timestamp(new Date())
                                .build())
                        .build();
            }catch (NullPointerException e){
                System.out.println(e);
                return Response.builder()
                        .status(401)
                        .error(ErrorResponse.builder()
                                .message("Error, es posible que el usuario y la contraseña esten incorrectas")
                                .timestamp(new Date())
                                .build())
                        .build();
            }
        }
}
