package com.uisarel.institucion.servicio.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilMenu;
import com.uisarel.institucion.modelo.repositorio.IPerfilMenuRepositorio;
import com.uisarel.institucion.servicio.IPerfilMenuServicio;

@Service
public class PerfilMenuServicioImpl implements IPerfilMenuServicio {

	@Autowired
	public IPerfilMenuRepositorio repositorioPerfilMenu;

	@Override
	public void insertarPefilMenu(PerfilMenu nuevoPerfilMenu) {
		// TODO Auto-generated method stub
		repositorioPerfilMenu.saveAll(Arrays.asList((nuevoPerfilMenu)));
	}

	@Override
	public List<PerfilMenu> listaPerfilMenu() {
		// TODO Auto-generated method stub
		return repositorioPerfilMenu.findAll();
	}

	@Override
	public void actualizarPerfilMenu(PerfilMenu perfilMenuEditado) {
		// TODO Auto-generated method stub
		repositorioPerfilMenu.save(perfilMenuEditado);
	}

	@Override
	public PerfilMenu buscarPerfilMenuId(int idPerfilMenu) {
		// TODO Auto-generated method stub
		return repositorioPerfilMenu.findById(idPerfilMenu).get();
	}

	@Override
	public void eliminarPerfilMenu(int idPerfilMenu) {
		// TODO Auto-generated method stub
		repositorioPerfilMenu.delete(buscarPerfilMenuId(idPerfilMenu));
	}

	@Override
	public void onSavePerfilMenu(int idperfil, int idmenu, String estado) {
		PerfilMenu perfilmenu = new PerfilMenu();

		try {
			// ELIMINAR ANTES DE GUARDAR PARA EVTAR DUPLICADOS
			PerfilMenu perfilMenuELiminar = repositorioPerfilMenu.findByFkPerfilIdPerfilAndFkMenuIdMenu(idperfil,
					idmenu);
			System.err.println(perfilmenu);
			if (perfilMenuELiminar != null) {
				repositorioPerfilMenu.deleteById(perfilMenuELiminar.getIdPerfilMenu());
			}

			// UNA VEZ ELIMINADO GURDAMOS
			perfilmenu.setEstado(estado);
			perfilmenu.setFechaCreacionPerMen(new Date());
			perfilmenu.setFechaModificacionPerMen(new Date());
			Perfil perfil = new Perfil();
			perfil.setIdPerfil(idperfil);
			perfilmenu.setFkPerfil(perfil);
			Menu menu = new Menu();
			menu.setIdMenu(idmenu);
			perfilmenu.setFkMenu(menu);

			repositorioPerfilMenu.save(perfilmenu);
		} catch (Exception e) {
			throw e;
		}

	}

//	@Autowired
//	private IPerfilMenuRepositorio repoPerfilMenu;
//	
//	@Autowired
//	private IMenuRepositorio repoMenu;
//
//	@Override
//	public List<PerfilMenu> onListarPerfilMenu() {
//		List<PerfilMenu> perilMenu = new ArrayList<>();
//		try {
//			perilMenu = repoPerfilMenu.findAll();
//		} catch (Exception e) {
//			throw e;
//		}
//		return perilMenu;
//	}
//

//
//	@Override
//	public void eliminarPerfilMenu(int idperfil, int idmenu) {
//		
//		try {
//			//BUSCAR SI EXISTE PARA ELIMINAR
//			//PerfilMenu perfilMenuELiminar = repoPerfilMenu.findByFkPerfilIdPerfilAndFkMenuIdMenu(idperfil, idmenu);
////			if(perfilMenuELiminar != null) {
////				repoPerfilMenu.deleteById(perfilMenuELiminar.getIdPerfilMenu());
////			}
//			
//		} catch (Exception e) {
//			throw e;
//		}
//		
//	}
//
//	@Override
//	public List<PerfilMenu> onSearchPerfilMenuAsigned(int idPerfil, int idMenu) {
//		List<PerfilMenu> menuPerfil = new ArrayList<>();
//		try {
//			//BUSCAR GRUPO DEL MENU
//			Menu menu = repoMenu.findById(idMenu).get();
//			
//			// menuPerfil = repoPerfilMenu.findByFkPerfilIdPerfilAndFkMenuMenuIdPadre(idPerfil, menu.getMenuIdPadre());			
//			
//		} catch (Exception e) {
//			throw e;
//		}
//		return menuPerfil;
//	}

}
