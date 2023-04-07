package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Conducta;

public interface IConductaService {

	/**
	 * @author JMISERVER13
	 * @param idestudiante
	 * @return
	 */
	List<Conducta> onListarConductaEstudiante(int idestudiante);

	/**
	 * @author JMISERVER13
	 * @param data
	 * @return
	 */
	Conducta onGuardarConducta(Conducta data);

	/**
	 * @author SOPORTE
	 * @param idconducta
	 * @param idcurso
	 * @param iddocente
	 * @return
	 */
	List<Conducta> onBuscarConductaAlumnoID(int idconducta, int idcurso, int iddocente);

	/**
	 * @author JMISERVER13
	 * @param dataUpdate
	 * @return
	 */
	Conducta onUpdateConductaEstudiante(Conducta dataUpdate);

	/**
	 * @author JMISERVER13
	 * @param idConducta
	 */
	void onEliminarConducta(int idConducta);
}
