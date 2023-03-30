package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Cursos;

public interface ICursosService {

	/**
	 * @author JMISERVER13
	 * @apiNote LISTA LOS CURSO
	 * @param estado
	 * @return
	 */
	List<Cursos> onListarCursos(String estado);

	/**
	 * @author JMISERVER13
	 * @param datacurso
	 * @return
	 */
	Cursos onGuardarCursoNuevo(Cursos datacurso);

	/**
	 * @author JMISERVER13
	 * @param idcurso
	 * @return
	 */
	Cursos onBuscarCursoID(int idcurso);

	/**
	 * @author JMISERVER13
	 * @param updatedata
	 * @return
	 */
	Cursos onUpdateCurso(Cursos updatedata);

	/**
	 * @author JMISERVER13
	 * @param idcurso
	 */
	void onEliminarCurso(int idcurso);
}
