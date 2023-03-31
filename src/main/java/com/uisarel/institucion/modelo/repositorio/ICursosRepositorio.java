package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Cursos;

@Repository
public interface ICursosRepositorio extends JpaRepository<Cursos, Integer> {

	List<Cursos> findByEstadoCurso(String estado);
}
