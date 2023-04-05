package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Conducta;

@Repository
public interface IConductaRepositorio extends JpaRepository<Conducta, Integer> {
	
	/**
	 * @author JMISERVER13
	 * @apiNote SELECT * FROM CONDUCTA WHERE ESTUDIANTE.IDESTUDIANTE = ?1
	 * @param idEstudiante
	 * @return
	 */
	List<Conducta> findByEstudianteIdEstudiante(int idEstudiante);
	
	/**
	 * @author SOPORTE
	 * @param idEstudiante
	 * @param idcurso
	 * @param idpersonal
	 * @return
	 */
	List<Conducta> findByEstudianteIdEstudianteAndCursoIdCursoAndPersonalIdPersonal(int idEstudiante, int idcurso, int idpersonal);

}
