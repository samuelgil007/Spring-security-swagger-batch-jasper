package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.Models.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Integer> {
    Medico findByIdUsuario(int id);

    @Override
    void deleteById(Integer integer);

    @Override
    List<Medico> findAll();

    Medico findById(int id);

    List<Medico> findByNombreAndApellido(String nombre, String apellido);
}
