package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Seccion;

public interface ISeccionService {

	/**
	 * @author JMISERVER13
	 * @return
	 */
	List<Seccion> onListarSeccionAll();

	/**
	 * @author JMISERVER13
	 * @param seccion
	 * @return
	 */
	Seccion onGuardarSeccionNuevo(Seccion seccion);

	/**
	 * @author JMISERVER13
	 * @param idseccion
	 * @return
	 */
	Seccion onBuscarSeccionID(int idseccion);

	/**
	 * @author JMISERVER13
	 * @param seccion
	 * @return
	 */
	Seccion onUpdateSeccion(Seccion seccion);

	/**
	 * @author JMISERVER13
	 * @param idseccion
	 */
	void onEliminarSeccionID(int idseccion);
}
