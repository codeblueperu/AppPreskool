package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.modelo.entidades.Menu;


public interface IMenuServicio {

//	
//	/**
//	 * @author JMISERVER13
//	 * @param principalMenu
//	 * @param estadoMenu
//	 * @return
//	 */
//	List<Menu> onListarMenuPrincipales(String principalMenu, String estadoMenu);
//	
//	/**
//	 * @author JMISERVER13
//	 * @param idmenu
//	 * @return
//	 */
//	List<Menu> onListarSubMenu(int idmenu);
	
public void insertarMenu(Menu nuevoMenu);
	
	public List<Menu> listarMenu();
	
	public void actualizarMenu(Menu menuEditado);
	
	public Menu buscarMenuId(int idMenu);
	
	public void eliminarMenu(int idMenu);
	
	//
	public List<Menu> listarMenus();
	//
	
	//SUB-MENU
	
	public void insertarSubMenu(Menu nuevoSubMenu);
	
	public List<Menu> listarSubMenu();
	
	public void actualizarSubMenu(Menu subMenuEditado);
	
	public Menu buscarSubMenuId(int idMenu);
	
	public void eliminarSubMenu(int idMenu);
	
	/**
	 * @author CodeBluePeru
	 * @param tipoMenu
	 * @param codMenu
	 * @param perfil
	 * @return
	 */
	public List<DtoMenuLogin> onBuscarMenuLogin();
	
	/**
	 * @author CodeBluePeru
	 *  @param ruta
	 * @return
	 */
	public boolean onValidarRutaPermiso(String ruta);
}
