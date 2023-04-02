package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;

public interface IAsignarTareaServicio {

	/**
	 * @author JMISERVER13
	 * @param idPeriodoEscolar
	 * @return
	 */
	List<AsignarTarea> onListarTareaPeriodoEscolarAperturado(int idPeriodoEscolar);

	/**
	 * @author JMISERVER13
	 * @param datos
	 * @return
	 */
	AsignarTarea onGuardarTareaNuevaPeriodoEscolar(AsignarTarea datos);

	/**
	 * @author JMISERVER13
	 * @param idTarea
	 * @return
	 */
	AsignarTarea onBuscarTareaIdPeriodoAprturado(int idTarea);

	/**
	 * @author JMISERVER13
	 * @param datosupdate
	 * @return
	 */
	AsignarTarea onUpdateTareaPeriodoEscolar(AsignarTarea datosupdate);

	/**
	 * @author JMISERVER13
	 * @param idtarea
	 */
	void onEliminarTareaIdPeridoEscolar(int idtarea);

	/**
	 * @author JMISERVER13
	 * @param indocente
	 * @param idcurso
	 * @param idgrado
	 * @param idseccion
	 * @return
	 */
	List<AsignarTarea> onListarTareaPeriodoEscolarAperturadoDocenteCursoGradoSeccion(int indocente, int idcurso,
			int idgrado, int idseccion);

}
