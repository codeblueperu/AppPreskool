package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.dto.DtoListaMenuPerfil;
import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.entidades.PerfilMenu;
import com.uisarel.institucion.modelo.repositorio.IPerfilMenuRepositorio;
import com.uisarel.institucion.utils.ConstantApp;

@Service
public class ConfiguracionesServiceImp {

	@Autowired
	private IPerfilMenuRepositorio repoPerfilmenu;

	public List<DtoListaMenuPerfil> onListaMenuPerfil(Authentication auth) {
		List<DtoListaMenuPerfil> listamenu = new ArrayList<>();
		try {
			//PRIMERO OBTENERMOS EL ROL DEL USUARIO			
			for (GrantedAuthority rol : auth.getAuthorities()) {
				ConstantApp.ROL_LOGIN = 	rol.getAuthority();
			}

			// LISTA MENU PRINCIPAL
			List<PerfilMenu> menuPricipal = repoPerfilmenu.findByFkPerfilNombreAndFkMenuOrden(ConstantApp.ROL_LOGIN, "0");

			for (PerfilMenu perfilMenu : menuPricipal) {
				if (perfilMenu.getEstado().compareTo("1") == 0) {

					DtoListaMenuPerfil dto = new DtoListaMenuPerfil();
					dto.setIdMenu(perfilMenu.getFkMenu().getIdMenu());
					dto.setNombre(perfilMenu.getFkMenu().getNombre());
					dto.setIconoMenu(perfilMenu.getFkMenu().getIconoMenu());
					dto.setMenuIdPadre(perfilMenu.getFkMenu().getMenuIdPadre());
					dto.setEstadoMenu(perfilMenu.getFkMenu().getEstadoMenu());
					dto.setuRL(perfilMenu.getFkMenu().getURL());
					dto.setOrden(perfilMenu.getFkMenu().getOrden());
//					PREPARAMOS LOS SUBMENUS
					List<PerfilMenu> menuHijos = repoPerfilmenu.findByFkPerfilNombreAndFkMenuMenuIdPadreAndFkMenuOrden(
							ConstantApp.ROL_LOGIN, perfilMenu.getFkMenu().getMenuIdPadre(), "1");
					
					List<Menu> prepareItems = new ArrayList<>();
					for (PerfilMenu menuperfil : menuHijos) {
						if(menuperfil.getEstado().compareTo("1") == 0) {
							Menu menuitems = new Menu();
							menuitems = menuperfil.getFkMenu();
							prepareItems.add(menuitems);
							dto.setSubMenus(prepareItems);
						}
						
					}
					listamenu.add(dto);
				}
			}
			// System.err.println(menuPricipal);

		} catch (Exception e) {
			throw e;
		}
		return listamenu;
	}

}
