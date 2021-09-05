package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.Models.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita,Integer> {

    void deleteById(int id);
    List<Cita> findAllByIdMedico(int id);
    List<Cita> findAllByIdPaciente(int id);
    @Override
    List<Cita> findAll();
    Cita findById(int id);
    Cita findByFechaInicioBeforeAndFechaFinAfterAndIdMedico(Date inicio, Date fin, int idMedico);
    Cita findByFechaInicioAndFechaFinAndIdMedico(Date inicio, Date fin, int idMedico);
    
    List<Cita> findByIdMedico(Integer id);
}
