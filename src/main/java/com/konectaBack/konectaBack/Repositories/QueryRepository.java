package com.konectaBack.konectaBack.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends CrudRepository<Object, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM bprqdfvzzqrujtkkjd6l.`Cita`" +
            " JOIN  bprqdfvzzqrujtkkjd6l.`Medico`" +
            " ON bprqdfvzzqrujtkkjd6l.`Cita`.id_medico=bprqdfvzzqrujtkkjd6l.`Medico`.id")
    List<Object> findCitaJoinMedico();
}

