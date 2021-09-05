package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.Models.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

    void deleteByIdusuario(int id);

    Rol findByIdusuario(int id);
}
