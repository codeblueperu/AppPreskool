package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;

public interface IPerfilOperacionesServicio {

	public void insertarPefilOperaciones(PerfilOperaciones nuevoPefilOperaciones);

	public List<PerfilOperaciones> listaPefilOperaciones();

	public void actualizarPerfilOperaciones(PerfilOperaciones perfilOperacionesEditado);

	public PerfilOperaciones buscarPerfilOperacionesId(int idPerfilOperaciones);

	public void eliminarPerfilOperaciones(int idPerfilOperaciones);

	// CONTROL DATOS DUPLICADOS
	public PerfilOperaciones PerfilOperacionesFkPerfil(Perfil fkperfil);

	public PerfilOperaciones PerfilOperacionesFkOperaciones(Operaciones fkOperaciones);

	/**
	 * @author CodeBluePeru
	 * @param idperfil
	 * @return
	 */
	public List<PerfilOperaciones> onBuscarPerfilOperacionAsinados(int idperfil);

}
