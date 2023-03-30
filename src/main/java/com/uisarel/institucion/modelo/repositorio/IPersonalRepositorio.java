package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Personal;

@Repository
public interface IPersonalRepositorio extends JpaRepository<Personal, Integer> {

}
