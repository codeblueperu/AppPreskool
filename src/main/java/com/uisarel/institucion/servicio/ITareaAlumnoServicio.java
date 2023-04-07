package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.TareaAlumno;

public interface ITareaAlumnoServicio {

	/**
	 * @author CodeBluePeru
	 * @param idTarea
	 * @param idgrado
	 * @param idseccion
	 * @param idtarea
	 * @return
	 */
	List<TareaAlumno> onBuscarTareaAlumno(int idGrado, int idSeccion, int idcurso, int idtarea);

	/**
	 * @author CodeBluePeru
	 * @param datos
	 * @return
	 */
	TareaAlumno onGuardarTareaALumno(TareaAlumno datos);

	/**
	 * @author CodeBluePeru
	 * @param idtareaAlumno
	 * @param idalumno
	 */
	void onEliminarTareaAlumno(int idtareaAlumno, int idalumno);
	/**
	 * @author CodeBluePeru
	 * @param idGrado
	 * @param idSeccion
	 * @param idcurso
	 * @param idtarea
	 * @return
	 */
	List<TareaAlumno> onDataDashBoardStudentTask(int idEstudiante,String estadoPresenta);
}
