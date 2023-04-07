package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Estudiante;

@Repository
public interface IEstudianteRepositori extends JpaRepository<Estudiante, Integer> {

	/**
	 * @author JMISERVER13
	 * @param numDocumento
	 * @param periodoEscolar
	 * @return
	 */
	Estudiante findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(String emailEstudiante, int periodoEscolar);

	/**
	 * @author JMISERVER13
	 * @param numDocumento
	 * @param periodoEscolar
	 * @return
	 */
	Estudiante findByNumDocumentoAndPeriodoEscolarIdPeriodoEscolar(String numDocumento, int periodoEscolar);

	/**
	 * @author JMISERVER13
	 * @param periodoEscolar
	 * @param nivelEscolar
	 * @param idGrado
	 * @param idSeccion
	 * @return
	 */
	List<Estudiante> findByPeriodoEscolarIdPeriodoEscolarAndNivelEscolarAndGradoAlumnoIdGradoAndSeccionAlumnoIdSeccion(
			int periodoEscolar, String nivelEscolar, int idGrado, int idSeccion);

	/**
	 * @author JMISERVER13
	 * @param idPeriodoEscolar
	 * @param idEstudiante
	 * @return
	 */
	Estudiante findByPeriodoEscolarIdPeriodoEscolarAndIdEstudiante(int idPeriodoEscolar, int idEstudiante);

	/**
	 * @author JMISERVER13
	 * @param periodoEscolar
	 * @return
	 */
	List<Estudiante> findByPeriodoEscolarIdPeriodoEscolar(int periodoEscolar);
	
	List<Estudiante> findByPeriodoEscolarIdPeriodoEscolarAndNivelEscolarAndGradoAlumnoIdGradoAndSeccionAlumnoIdSeccionAndTurno(
			int periodoEscolar, String nivelEscolar, int idGrado, int idSeccion,String turno);
	
	
}
