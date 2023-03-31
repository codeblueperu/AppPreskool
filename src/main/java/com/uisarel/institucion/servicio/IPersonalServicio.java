package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Personal;

public interface IPersonalServicio {

	/**
	 * @author JMISERVER13
	 * @return
	 */
	List<Personal> onListarPersonalAll();

	/**
	 * @author JMISERVER13
	 * @param data
	 * @return
	 */
	Personal onGuardarDatosPersonal(Personal data);

	/**
	 * @author JMISERVER13
	 * @param idPersonal
	 * @return
	 */
	Personal onBuscarPersonalId(int idPersonal);

	/**
	 * @author JMISERVER13
	 * @param dataPersonal
	 * @return
	 */
	Personal onUpdateDataPersonal(Personal dataPersonal);

	/**
	 * @author JMISERVER13
	 * @param idpersonal
	 */
	void onEliminarDataPersonalID(int idpersonal);

	/**
	 * @author JMISERVER13
	 * @param idPersonal
	 * @return
	 */
	Personal onBuscarPersonalGradoSeccionCursoId(int idPersonal);
}
