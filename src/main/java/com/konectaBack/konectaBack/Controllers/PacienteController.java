package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.DTOs.PacienteDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Paciente;
import com.konectaBack.konectaBack.Services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(ControllerConstants.FRONT_END)
public class PacienteController {


    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping(ControllerConstants.PACIENTE)
    public ResponseEntity<Object> retornarPacientes(){
        return new ResponseEntity<>(pacienteService.retornarPacientes(), HttpStatus.OK);
    }

    @PostMapping(value = ControllerConstants.PACIENTE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearPaciente(@Valid @RequestBody PacienteDTO paciente){
        Response response = pacienteService.crearPaciente(paciente);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
