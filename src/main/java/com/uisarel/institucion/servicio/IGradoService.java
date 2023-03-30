package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Grado;

public interface IGradoService {

	/**
	 * @author JMISERVER13
	 * @return
	 */
	List<Grado> onListarGradosAll();

	/**
	 * @author JMISERVER13
	 * @param grado
	 * @return
	 */
	Grado onGuardarGradoNuevo(Grado grado);

	/**
	 * @author JMISERVER13
	 * @param idgrado
	 * @return
	 */
	Grado onBuscarGradoId(int idgrado);

	/**
	 * @author JMISERVER13
	 * @param grado
	 * @return
	 */
	Grado onUpdateGrado(Grado grado);

	/**
	 * @author JMISERVER13
	 * @param idgrado
	 */
	void onEliminarGrado(int idgrado);
}
