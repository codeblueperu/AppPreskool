package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.repositorio.IMenuRepositorio;
import com.uisarel.institucion.servicio.IMenuServicio;

@Service
public class MenuServicioImpl implements IMenuServicio {

	@Autowired
	private IMenuRepositorio repoMenu;
	
	@Override
	public List<Menu> onListarMenuPrincipales(String principalMenu, String estadoMenu) {
		List<Menu> lista = new ArrayList<>();
		try {
			lista = repoMenu.findByOrdenAndEstadoMenuOrderByMenuIdPadreAsc(principalMenu, estadoMenu);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public List<Menu> onListarSubMenu(int idmenu) {
		List<Menu> lista = new ArrayList<>();
		try {
			Menu menu = repoMenu.findById(idmenu).get();
			
			lista = repoMenu.findByMenuIdPadreAndOrdenAndEstadoMenu(menu.getMenuIdPadre(),"1", "1");
			lista.add(menu);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

}
