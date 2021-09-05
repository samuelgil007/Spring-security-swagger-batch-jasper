package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Services.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(ControllerConstants.FRONT_END)
public class CitaController {

    private CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping(value = ControllerConstants.CITA, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> crearCita(@Valid @RequestBody CitaDTO cita){
        Response response = citaService.crearCita(cita);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping(ControllerConstants.CITA)
    public ResponseEntity<Object> retornarCitas(){
        return new ResponseEntity<>(citaService.retornarCitas(), HttpStatus.OK);
    }

    @GetMapping(ControllerConstants.CITA_MEDICO_ID)
    public ResponseEntity<Object> retornarCitasPorMedico(@PathVariable int id){
        return new ResponseEntity<>(citaService.retornarCitasPorMedico(id), HttpStatus.OK);
    }
    @GetMapping(ControllerConstants.CITA_PACIENTE_ID)
    public ResponseEntity<Object> retornarCitasPorPaciente(@PathVariable int id){
        return new ResponseEntity<>(citaService.retornarCitasPorPaciente(id), HttpStatus.OK);
    }

    @DeleteMapping(value = ControllerConstants.CITA_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> borrarCita(@PathVariable int id){
        Response response = citaService.borrarCita(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping(value = ControllerConstants.CITA_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> editarCita(@Valid @RequestBody CitaDTO cita, @PathVariable int id){
        Response response = citaService.modificarCita(cita, id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
