package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.PerfilMenu;

public interface IPerfilMenuServicio {

	public void insertarPefilMenu(PerfilMenu nuevoPerfilMenu);

	public List<PerfilMenu> listaPerfilMenu();

	public void actualizarPerfilMenu(PerfilMenu perfilMenuEditado);

	public PerfilMenu buscarPerfilMenuId(int idPerfilMenu);

	public void eliminarPerfilMenu(int idPerfilMenu);

//	/**
//	 * @author JMISERVER13
//	 * @apiNote LISTA DE PERFIL Y SUS MENUS
//	 * @return
//	 */
//	List<PerfilMenu> onListarPerfilMenu();
//	
	/**
	 * @author JMISERVER13
	 * @apiNote GUARDAR PERFIL MENU
	 * @param perfilmenu
	 */
	void onSavePerfilMenu(int idperfil, int idmenu, String estado);
//	
//	/**
//	 * @author JMISERVER13
//	 * @apiNote ELIMINAR ANTES DE GUARDAR EL NUEVO CAMBIO
//	 * @param idperfil
//	 * @param idmenu
//	 */
//	void eliminarPerfilMenu(int idperfil,int idmenu);
//	
//	/**
//	 * @author JMISERVER13
//	 * @param idPerfil
//	 * @return
//	 */
//	List<PerfilMenu> onSearchPerfilMenuAsigned(int idPerfil, int idMenu);

}
