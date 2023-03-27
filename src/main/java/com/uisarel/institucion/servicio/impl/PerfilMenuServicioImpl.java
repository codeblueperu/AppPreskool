package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilMenu;
import com.uisarel.institucion.modelo.repositorio.IMenuRepositorio;
import com.uisarel.institucion.modelo.repositorio.IPerfilMenuRepositorio;
import com.uisarel.institucion.servicio.IPerfilMenuServicio;

@Service
public class PerfilMenuServicioImpl implements IPerfilMenuServicio {

	@Autowired
	private IPerfilMenuRepositorio repoPerfilMenu;
	
	@Autowired
	private IMenuRepositorio repoMenu;

	@Override
	public List<PerfilMenu> onListarPerfilMenu() {
		List<PerfilMenu> perilMenu = new ArrayList<>();
		try {
			perilMenu = repoPerfilMenu.findAll();
		} catch (Exception e) {
			throw e;
		}
		return perilMenu;
	}

	@Override
	public void onSavePerfilMenu(int idperfil, int idmenu,String estado) {
		PerfilMenu perfilmenu = new PerfilMenu();
		
		try {
			//ELIMINAR ANTES DE GUARDAR PARA EVTAR DUPLICADOS
			eliminarPerfilMenu(idperfil, idmenu);
			//UNA VEZ ELIMINADO GURDAMOS
			perfilmenu.setEstado(estado);
			perfilmenu.setFechaCreacionPerMen(new Date());
			perfilmenu.setFechaModificacionPerMen(new Date());
			Perfil perfil = new Perfil();
			perfil.setIdPerfil(idperfil);			
			perfilmenu.setFkPerfil(perfil);
			Menu menu = new Menu();
			menu.setIdMenu(idmenu);
			perfilmenu.setFkMenu(menu);
			
			repoPerfilMenu.save(perfilmenu);
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public void eliminarPerfilMenu(int idperfil, int idmenu) {
		
		try {
			//BUSCAR SI EXISTE PARA ELIMINAR
			PerfilMenu perfilMenuELiminar = repoPerfilMenu.findByFkPerfilIdPerfilAndFkMenuIdMenu(idperfil, idmenu);
			if(perfilMenuELiminar != null) {
				repoPerfilMenu.deleteById(perfilMenuELiminar.getIdPerfilMenu());
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public List<PerfilMenu> onSearchPerfilMenuAsigned(int idPerfil, int idMenu) {
		List<PerfilMenu> menuPerfil = new ArrayList<>();
		try {
			//BUSCAR GRUPO DEL MENU
			Menu menu = repoMenu.findById(idMenu).get();
			
			 menuPerfil = repoPerfilMenu.findByFkPerfilIdPerfilAndFkMenuMenuIdPadre(idPerfil, menu.getMenuIdPadre());			
			
		} catch (Exception e) {
			throw e;
		}
		return menuPerfil;
	}

}
