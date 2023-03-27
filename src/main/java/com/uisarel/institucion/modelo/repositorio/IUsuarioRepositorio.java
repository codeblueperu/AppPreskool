package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Usuario;

@Repository
@Component
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	Usuario findByUsuarioCorreo(String username);
	
}
