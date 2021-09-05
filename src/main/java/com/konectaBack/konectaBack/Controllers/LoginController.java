package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import com.konectaBack.konectaBack.DTOs.LoginDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = ControllerConstants.FRONT_END)
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(ControllerConstants.LOGIN)
    public ResponseEntity<Response> crearUsuario(@Valid @RequestBody LoginDTO login){
        Response response = loginService.realizarLogin(login);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
