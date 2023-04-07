package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Estudiante;

public interface IEstudianteService {

	/**
	 * @author JMISERVER13
	 * @param periodoEscolar
	 * @return
	 */
	List<Estudiante> onListarEstuandePeriodoEscolar(int periodoEscolar);

	/**
	 * @author JMISERVER13
	 * @param dataEstudiante
	 * @return
	 */
	Estudiante onGuardarEstudianteNuevo(Estudiante dataEstudiante);

	/**
	 * @author JMISERVER13
	 * @param idUsuario
	 * @return
	 */
	Estudiante onBuscarEstudianteId(int idUsuario);
	
	Estudiante onBuscarEstudiantePeriodoEscolarId(int idEstudiante,int idperiodoEscolar);

	/**
	 * @author JMISERVER13
	 * @param dataUpdate
	 * @return
	 */
	Estudiante onUpdateEstudiantePeriodo(Estudiante dataUpdate);

	/**
	 * @author JMISERVER13
	 * @param idEstudiante
	 */
	void onEliminarEstudiantePeriodoId(int idEstudiante);

	/**
	 * @author JMISERVER13
	 * @param periodoEscolar
	 * @param nivelEscolar
	 * @param idGrado
	 * @param idSeccion
	 * @return
	 */
	List<Estudiante> onListarEstuandePeriodoEscolarGradoSeccionNivel(int periodoEscolar, String nivelEscolar,
			int idGrado, int idSeccion);
	
	List<Estudiante> onListarEstuandePeriodoEscolarGradoSeccionNivelTurno(int periodoEscolar, String nivelEscolar,
			int idGrado, int idSeccion,String turno);
}
