package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.DTOs.UsuarioDTO;
import com.konectaBack.konectaBack.Services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(ControllerConstants.FRONT_END)
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(ControllerConstants.USUARIO)
    public ResponseEntity<Object> retornarUsuarios(){
        return new ResponseEntity<>(usuarioService.retornarUsuarios(), HttpStatus.OK);
     }

    @PostMapping(value = ControllerConstants.USUARIO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearUsuario(@Valid @RequestBody UsuarioDTO usuario){
        Response response = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
