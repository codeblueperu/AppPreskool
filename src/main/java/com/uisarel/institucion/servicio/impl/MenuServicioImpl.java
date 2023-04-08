package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.repositorio.IMenuRepositorio;
import com.uisarel.institucion.servicio.IMenuServicio;

@Service
@Component
public class MenuServicioImpl implements IMenuServicio {

	@Autowired
	private IMenuRepositorio menuRepositorio;

	@Override
	public List<Menu> listarMenu() {
		List<Menu> lista = menuRepositorio.listarMenu();
		//System.err.println(lista);
		return lista;
	}

	@Override
	public void insertarMenu(Menu nuevoMenu) {
		// TODO Auto-generated method stub
		menuRepositorio.save(nuevoMenu);
	}

	@Override
	public void actualizarMenu(Menu menuEditado) {
		// TODO Auto-generated method stub
		menuRepositorio.save(menuEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public Menu buscarMenuId(int idMenu) {
		// TODO Auto-generated method stub
		return menuRepositorio.findById(idMenu).get();
	}

	@Override
	@Transactional
	public void eliminarMenu(int idMenu) {
		// TODO Auto-generated method stub
		menuRepositorio.delete(buscarMenuId(idMenu));
	}

	// SUB-MENU

	@Override
	public void insertarSubMenu(Menu nuevoSubMenu) {
		// TODO Auto-generated method stub
		menuRepositorio.save(nuevoSubMenu);
	}

	@Override
	public List<Menu> listarSubMenu() {
		// TODO Auto-generated method stub
		return menuRepositorio.listarSubMenu();
	}

	@Override
	public void actualizarSubMenu(Menu subMenuEditado) {
		// TODO Auto-generated method stub
		menuRepositorio.save(subMenuEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public Menu buscarSubMenuId(int idMenu) {
		// TODO Auto-generated method stub
		return menuRepositorio.findById(idMenu).get();
	}

	@Override
	@Transactional
	public void eliminarSubMenu(int idMenu) {
		// TODO Auto-generated method stub
		menuRepositorio.delete(buscarSubMenuId(idMenu));
	}

	@Override
	public List<Menu> listarMenus() {
		// TODO Auto-generated method stub
		return menuRepositorio.findAll();
	}

//	@Autowired
//	private IMenuRepositorio repoMenu;
//	
//	@Override
//	public List<Menu> onListarMenuPrincipales(String principalMenu, String estadoMenu) {
//		List<Menu> lista = new ArrayList<>();
//		try {
//			//lista = repoMenu.findByOrdenAndEstadoMenuOrderByMenuIdPadreAsc(principalMenu, estadoMenu);
//		} catch (Exception e) {
//			throw e;
//		}
//		return lista;
//	}
//
//	@Override
//	public List<Menu> onListarSubMenu(int idmenu) {
//		List<Menu> lista = new ArrayList<>();
//		try {
//			Menu menu = repoMenu.findById(idmenu).get();
//			
//			//lista = repoMenu.findByMenuIdPadreAndOrdenAndEstadoMenu(menu.getMenuIdPadre(),"1", "1");
//			lista.add(menu);
//		} catch (Exception e) {
//			throw e;
//		}
//		return lista;
//	}

}
