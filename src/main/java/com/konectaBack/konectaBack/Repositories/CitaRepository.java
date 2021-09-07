package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.DTOs.CitaDTO;
import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Models.Queries.CitaJoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita,Integer> {
    void deleteById(int id);
    List<Cita> findAllByIdMedico(Medico medico);
    List<Cita> findAllByIdPaciente(int id);
    @Override
    List<Cita> findAll();
    Cita findById(int id);
    Cita findByFechaInicioBeforeAndFechaFinAfterAndIdMedico(Date inicio, Date fin, Medico idMedico);
    Cita findByFechaInicioAndFechaFinAndIdMedico(Date inicio, Date fin, Medico idMedico);

    @Query("SELECT new com.konectaBack.konectaBack.Models.Queries.CitaJoin(c.id ," +
            " c.fechaInicio, c.fechaFin, c.tipoAtencion, p.nombre, p.apellido) " +
            "FROM Cita c JOIN c.idMedico p")
    List<CitaJoin> findJoinByIdMedico();
}
