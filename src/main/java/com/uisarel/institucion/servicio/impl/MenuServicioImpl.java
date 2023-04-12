package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.repositorio.IMenuRepositorio;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.utils.ConstantApp;

@Service
@Component
public class MenuServicioImpl implements IMenuServicio {

	@Autowired
	private IMenuRepositorio menuRepositorio;

	@Autowired
	private NativeQueryJDBC srvNative;

	@Override
	public List<Menu> listarMenu() {
		List<Menu> lista = menuRepositorio.listarMenu();
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
	public List<DtoMenuLogin> onBuscarMenuLogin() {
		List<DtoMenuLogin> menuLogin = new ArrayList<>();
		try {
			List<DtoMenuLogin> menuPrincipales = srvNative.onListarMenuLoginUsuario("MP", 0, ConstantApp.getuRolUser());
			for (DtoMenuLogin mnuLista : menuPrincipales) {
				List<DtoMenuLogin> subMenu = srvNative.onListarMenuLoginUsuario("SUBM", mnuLista.getIdMenu(),
						ConstantApp.getuRolUser());
				mnuLista.setSubMenu(subMenu);
				menuLogin.add(mnuLista);
			}
		} catch (Exception e) {
			throw e;
		}
		//System.err.println(menuLogin);
		return menuLogin;
	}

	@Override
	public boolean onValidarRutaPermiso(String ruta) {
		boolean status = false;
		try {
			if(srvNative.onValidarPermisoRuta(ruta, ConstantApp.getuRolUser()) > 0) {
				status = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return status;
	}

	@Override
	public List<Menu> listarMenuPrincipales() {
		
		return menuRepositorio.listarMenu();
	}

	@Override
	public List<DtoMenuLogin> onBuscarSubmenu(int idMenuPrincipal) {
		// TODO Auto-generated method stub
		return srvNative.onListarMenuSubmenu(idMenuPrincipal);
	}

	@Override
	public List<DtoMenuLogin> onBuscarMenuusuarioPerfil(int codMenu, int idperfil) {
		// TODO Auto-generated method stub
		return srvNative.onListarMenuUsuarioPerfil(codMenu, idperfil);
	}

}
