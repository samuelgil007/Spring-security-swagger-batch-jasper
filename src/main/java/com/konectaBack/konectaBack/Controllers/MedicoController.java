package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import com.konectaBack.konectaBack.DTOs.MedicoDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Services.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(ControllerConstants.FRONT_END)
public class MedicoController {

    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping(value = ControllerConstants.MEDICO, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearMedico(@Valid @RequestBody MedicoDTO medico){
        Response response = medicoService.crearMedico(medico);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping(value = ControllerConstants.MEDICO_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> borrarMedico(@PathVariable int id){
        Response response = medicoService.borrarMedico(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping(ControllerConstants.MEDICO)
    public ResponseEntity<Object> retornarMedicos(){
        return new ResponseEntity<>(medicoService.retornarMedicos(), HttpStatus.OK);
    }

    @PostMapping(ControllerConstants.MEDICO_ID_DESACTIVATE)
    public ResponseEntity<Response> desactivarMedico(@PathVariable int id){
        Response response = medicoService.desactivarMedico(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    @PostMapping(ControllerConstants.MEDICO_ID_ACTIVATE)
    public ResponseEntity<Response> activarMedico(@PathVariable int id){
        Response response = medicoService.activarMedico(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
