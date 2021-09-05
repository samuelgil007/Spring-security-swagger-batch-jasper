package com.konectaBack.konectaBack.Repositories;

import com.konectaBack.konectaBack.Models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Override
    List<Usuario> findAll ();

    @Override
    void deleteById(Integer integer);

    Usuario findById(int id);

    Usuario findByEmailAndPassword(String email, String password);

}
