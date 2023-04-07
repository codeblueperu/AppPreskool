package com.uisarel.institucion.servicio;

import java.util.Date;
import java.util.List;

import com.uisarel.institucion.modelo.entidades.Asistencia;

public interface IAsistenciaServicio {

	/**
	 * @author CodeBluePeru
	 * @param idcurso
	 * @param fecha
	 * @return
	 */
	List<Asistencia> onListarAsistenciaCursoFecha(int idcurso, int idseccion, Date fecha);

	/**
	 * @author CodeBluePeru
	 * @param idasistencia
	 * @return
	 */
	Asistencia onBuscarAsistenciaCursoFechaId(int idasistencia);

	/**
	 * @author CodeBluePeru
	 * @param data
	 * @return
	 */
	Asistencia onGuardarAsistenciaCursoFecha(Asistencia data);

	/**
	 * @author CodeBluePeru
	 * @param idasistencia
	 * @param idcurso
	 * @param fechaAsistencia
	 */
	void onEliminarAsistenciaCursoFecha(int idasistencia);
	
	/**
	 * @author CodeBluePeru
	 * @param idEstudiante
	 * @param estadoAsistencia
	 * @return
	 */
	List<Asistencia> onDataDashBoardStudent(int idEstudiante, String estadoAsistencia);
}
