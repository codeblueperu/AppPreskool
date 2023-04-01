package com.uisarel.institucion.servicio;

import java.util.Date;
import java.util.List;

import com.uisarel.institucion.modelo.entidades.Asistencia;

public interface IAsistenciaServicio {

	/**
	 * @author JMISERVER13
	 * @param idcurso
	 * @param fecha
	 * @return
	 */
	List<Asistencia> onListarAsistenciaCursoFecha(int idcurso, int idseccion, Date fecha);

	/**
	 * @author JMISERVER13
	 * @param idasistencia
	 * @return
	 */
	Asistencia onBuscarAsistenciaCursoFechaId(int idasistencia);

	/**
	 * @author JMISERVER13
	 * @param data
	 * @return
	 */
	Asistencia onGuardarAsistenciaCursoFecha(Asistencia data);

	/**
	 * @author JMISERVER13
	 * @param idasistencia
	 * @param idcurso
	 * @param fechaAsistencia
	 */
	void onEliminarAsistenciaCursoFecha(int idasistencia);
}
