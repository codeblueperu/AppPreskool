package com.uisarel.institucion.servicio;


import java.util.Date;
import java.util.List;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;

public interface IAsignarTareaServicio {

	/**
	 * @author CodeBluePeru
	 * @param idPeriodoEscolar
	 * @return
	 */
	List<AsignarTarea> onListarTareaPeriodoEscolarAperturado(int idPeriodoEscolar);

	/**
	 * @author CodeBluePeru
	 * @param datos
	 * @return
	 */
	AsignarTarea onGuardarTareaNuevaPeriodoEscolar(AsignarTarea datos);

	/**
	 * @author CodeBluePeru
	 * @param idTarea
	 * @return
	 */
	AsignarTarea onBuscarTareaIdPeriodoAprturado(int idTarea);

	/**
	 * @author CodeBluePeru
	 * @param datosupdate
	 * @return
	 */
	AsignarTarea onUpdateTareaPeriodoEscolar(AsignarTarea datosupdate);

	/**
	 * @author CodeBluePeru
	 * @param idtarea
	 */
	void onEliminarTareaIdPeridoEscolar(int idtarea);

	/**
	 * @author CodeBluePeru
	 * @param indocente
	 * @param idcurso
	 * @param idgrado
	 * @param idseccion
	 * @return
	 */
	List<AsignarTarea> onListarTareaPeriodoEscolarAperturadoDocenteCursoGradoSeccion(int indocente, int idcurso,
			int idgrado, int idseccion);
	
	/**
	 * @author CodeBluePeru
	 * @param idGrado
	 * @param idseccion
	 * @param nivelEscolar
	 * @param fechaVigente
	 * @return
	 */
	List<AsignarTarea> onListarTareasActivas(int idGrado, int idseccion, String nivelEscolar, Date fechaVigente);

}
