package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.TareaAlumno;

public interface ITareaAlumnoServicio {

	/**
	 * @author JMISERVER13
	 * @param idTarea
	 * @param idgrado
	 * @param idseccion
	 * @param idtarea
	 * @return
	 */
	List<TareaAlumno> onBuscarTareaAlumno(int idGrado, int idSeccion, int idcurso, int idtarea);

	/**
	 * @author JMISERVER13
	 * @param datos
	 * @return
	 */
	TareaAlumno onGuardarTareaALumno(TareaAlumno datos);

	/**
	 * @author JMISERVER13
	 * @param idtareaAlumno
	 * @param idalumno
	 */
	void onEliminarTareaAlumno(int idtareaAlumno, int idalumno);
}
