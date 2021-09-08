package com.konectaBack.konectaBack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konectaBack.konectaBack.Controllers.CitaController;
import com.konectaBack.konectaBack.DTOMappers.CitaDTOMapper;
import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.DTOs.Responses.Response;
import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Repositories.CitaRepository;
import com.konectaBack.konectaBack.Repositories.MedicoRepository;
import com.konectaBack.konectaBack.Services.CitaService;
import com.konectaBack.konectaBack.Services.LoginService;
import com.konectaBack.konectaBack.Services.MedicoService;
import com.konectaBack.konectaBack.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class CitaControllerIntegrationTests {

    @MockBean
    public CitaRepository citaRepository;

    @MockBean
    public MedicoRepository medicoRepository;

    @Mock
    public CitaService citaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginService loginService;

    private String token;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public void generarToken(){
        token = JwtUtils.generarToken(loginService.loadUserByUsername("samuel@corre.com"));
    }

    @Test
    public void saveCita() throws Exception {
        Medico medico = new Medico();
        medico.setId(1);
        CitaDTO citaDTO = new CitaDTO(
                1,new Date(), new Date(),1,1,"sad", "as");
        Cita cita = CitaDTOMapper.mapearCita(citaDTO, medico);
        Mockito.when(citaRepository.save(cita)).thenReturn(cita);
        Response response = new Response();
        response.setPayload(cita);
        Mockito.when(citaService.crearCita(citaDTO)).thenReturn(response);

        this.mockMvc.perform(post("/cita").header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(citaDTO))
        ).andDo(print()).andExpect(status().isCreated());
    }
}
