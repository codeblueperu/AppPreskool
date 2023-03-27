package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Perfil;

@Repository
@Component
public interface IPerfilRepositorio extends JpaRepository<Perfil, Integer> {

}
