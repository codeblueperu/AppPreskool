package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;

public interface IPerfilOperacionesServicio {
	
	public void insertarPefilOperaciones(PerfilOperaciones nuevoPefilOperaciones);
	
	public List<PerfilOperaciones> listaPefilOperaciones();
	
	public void actualizarPerfilOperaciones(PerfilOperaciones perfilOperacionesEditado);
	
	public PerfilOperaciones buscarPerfilOperacionesId(int idPerfilOperaciones);
	
	public void eliminarPerfilOperaciones(int idPerfilOperaciones);
	
	/**
	 * @author JMISERVER13
	 * @param idoperation
	 * @param idperfil
	 * @return
	 */
	public void onSearchPerfilOperacionAllDelete(int idoperation, int idperfil);
	
	/**
	 * @author JMISERVER13
	 * @param idPerfil
	 * @return
	 */
	public List<PerfilOperaciones> onSearchPerfilOperationAsigned(int idPerfil,int idmenu);
	
	
	public PerfilOperaciones onBuscarPermidoRolMenu(int idmenu);
		

}
