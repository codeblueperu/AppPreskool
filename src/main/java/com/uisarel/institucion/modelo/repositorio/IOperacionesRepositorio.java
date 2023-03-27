package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Operaciones;

@Repository
@Component
public interface IOperacionesRepositorio extends JpaRepository<Operaciones, Integer>{

}
