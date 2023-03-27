package com.uisarel.institucion.servicio;

import java.util.List;


import com.uisarel.institucion.modelo.entidades.Menu;

public interface IMenuServicio {

	
	/**
	 * @author JMISERVER13
	 * @param principalMenu
	 * @param estadoMenu
	 * @return
	 */
	List<Menu> onListarMenuPrincipales(String principalMenu, String estadoMenu);
	
	/**
	 * @author JMISERVER13
	 * @param idmenu
	 * @return
	 */
	List<Menu> onListarSubMenu(int idmenu);
}
