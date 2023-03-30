package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;

public interface IPeriodoEscolarService {

	/**
	 * @author JMISERVER13
	 * @apiNote LISTA TODO LOS PERIODOS CREADOS
	 * @return
	 */
	List<PeriodoEscolar> onListarPeriodoEscolarAll();

	/**
	 * @author JMISERVER13
	 * @apiNote GUARDA UN NUEVO PERIODO
	 * @param dataperiodo
	 * @return
	 */
	PeriodoEscolar onGuardarNuevoPeriodoEscolar(PeriodoEscolar dataperiodo);

	/**
	 * @author JMISERVER13
	 * @apiNote BUSCAR UN PERIODO ESCOLAR EN ESPECIFICO POR ID
	 * @param idPeriodo
	 * @return
	 */
	PeriodoEscolar onBuscarPeriodoEscolarID(int idPeriodo);

	/**
	 * @author JMISERVER13
	 * @apiNote ACTUALIZA LOS DATOS DEL PERIODO
	 * @param updateData
	 * @return
	 */
	PeriodoEscolar onUpdatePeriodoEscolar(PeriodoEscolar updateData);

	/**
	 * @author JMISERVER13
	 * @apiNote ELIMINA UN PERIODO ESCOLAR
	 * @param idperiodo
	 */
	void onEliminarPeriodoEscolar(int idperiodo);

	/**
	 * @author JMISERVER13
	 * @param estado
	 * @return
	 */
	List<PeriodoEscolar> onListarPeriodoEscolarEstado(String estado);
}
