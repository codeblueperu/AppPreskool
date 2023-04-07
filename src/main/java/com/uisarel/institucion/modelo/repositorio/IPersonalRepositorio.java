package com.uisarel.institucion.modelo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Personal;

@Repository
public interface IPersonalRepositorio extends JpaRepository<Personal, Integer> {

	/**
	 * @author CodeBluePeru
	 * @param numDocumento
	 * @return
	 */
	Optional<Personal> findByNumDocumento(String numDocumento);
	
	/**
	 * @author CodeBluePeru
	 * @param email_login
	 * @return
	 */
	List<Personal> findByEmail(String email_login);
	/**
	 * @author CodeBluePeru
	 * @param nivelAcademico
	 * @param idGrado
	 * @param idSeccion
	 * @return
	 */
	List<Personal> findByNivelAcademicoAndLstGradoIdGradoAndLstSeccionIdSeccion(String nivelAcademico,int idGrado,int idSeccion);
	
}
