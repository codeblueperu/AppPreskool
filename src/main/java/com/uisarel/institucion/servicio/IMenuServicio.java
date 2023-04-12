package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.modelo.entidades.Menu;

public interface IMenuServicio {

	public void insertarMenu(Menu nuevoMenu);

	public List<Menu> listarMenu();

	public void actualizarMenu(Menu menuEditado);

	public Menu buscarMenuId(int idMenu);

	public void eliminarMenu(int idMenu);

	public List<Menu> listarMenuPrincipales();

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
	 * @param ruta
	 * @return
	 */
	public boolean onValidarRutaPermiso(String ruta);
	
	/**
	 * @author CodeBluePeru
	 * @param idMenuPrincipal
	 * @return
	 */
	public List<DtoMenuLogin> onBuscarSubmenu(int idMenuPrincipal);
	
	/**
	 * @author CodeBluePeru
	 * @param codMenu
	 * @param idperfil
	 * @return
	 */
	public List<DtoMenuLogin> onBuscarMenuusuarioPerfil(int codMenu, int idperfil);
}
