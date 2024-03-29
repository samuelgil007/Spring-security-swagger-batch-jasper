package com.konectaBack.konectaBack;


import com.konectaBack.konectaBack.Controllers.CitaController;
import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Repositories.CitaRepository;
import com.konectaBack.konectaBack.Repositories.MedicoRepository;
import com.konectaBack.konectaBack.Services.CitaService;
import com.konectaBack.konectaBack.Services.LoginService;
import com.konectaBack.konectaBack.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Slf4j
public class CitaControllerTests {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaService citaService = new CitaService(citaRepository,medicoRepository);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CitaController citaController = new CitaController(citaService);

    //Unitarias para los controladores
    @Test
    public void findAll() throws Exception {
        ResponseEntity<List<Cita>> response = citaController.retornarCitas();
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

        List<Cita> citas = new ArrayList<>();
        Assertions.assertEquals(citas.getClass(), response.getBody().getClass());
    }
    @Test
    public void findbyIdPaciente() throws Exception {
        ResponseEntity<List<Cita>> response = citaController.retornarCitasPorPaciente(1);
        Assertions.assertEquals(1, response.getBody().get(0).getIdPaciente());
        List<Cita> citas = new ArrayList<>();
        Assertions.assertEquals(citas.getClass(), response.getBody().getClass());
    }

}
