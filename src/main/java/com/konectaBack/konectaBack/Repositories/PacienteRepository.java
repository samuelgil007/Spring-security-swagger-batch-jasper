package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.Models.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente,Integer> {

    Paciente findByIdUsuario(int id);
    List<Paciente> findAll();
    Paciente findByCedula(String cedula);
}
