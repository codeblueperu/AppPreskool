package com.uisarel.institucion.modelo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Personal;

@Repository
public interface IPersonalRepositorio extends JpaRepository<Personal, Integer> {

	/**
	 * @author JMISERVER13
	 * @param numDocumento
	 * @return
	 */
	Optional<Personal> findByNumDocumento(String numDocumento);
	
	List<Personal> findByEmail(String email_login);
}
