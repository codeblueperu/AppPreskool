package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.modelo.repositorio.IPerfilOperacionesRepositorio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.utils.ConstantApp;

@Service
@Component
public class PerfilOperacionesSevicioImpl implements IPerfilOperacionesServicio {

	@Autowired
	public IPerfilOperacionesRepositorio repositorioPerfilOperaciones;

	@Override
	public void insertarPefilOperaciones(PerfilOperaciones nuevoPefilOperaciones) {
		repositorioPerfilOperaciones.save(nuevoPefilOperaciones);
	}

	@Override
	public List<PerfilOperaciones> listaPefilOperaciones() {
		// TODO Auto-generated method stub
		return repositorioPerfilOperaciones.findAll();
	}

	@Override
	public void actualizarPerfilOperaciones(PerfilOperaciones perfilOperacionesEditado) {
		// TODO Auto-generated method stub
		repositorioPerfilOperaciones.save(perfilOperacionesEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public PerfilOperaciones buscarPerfilOperacionesId(int idPerfilOperaciones) {
		// TODO Auto-generated method stub
		return repositorioPerfilOperaciones.findById(idPerfilOperaciones).get();
	}

	@Override
	@Transactional
	public void eliminarPerfilOperaciones(int idPerfilOperaciones) {
		// TODO Auto-generated method stub
		repositorioPerfilOperaciones.delete(buscarPerfilOperacionesId(idPerfilOperaciones));
	}

	@Override
	@Transactional
	public void onSearchPerfilOperacionAllDelete(int idmenu, int idperfil) {
		try {
			List<PerfilOperaciones> ope = repositorioPerfilOperaciones.findByFkPerfilIdPerfilAndFkMenuIdMenu(idperfil,
					idmenu);
			System.err.println(ope);
			if (ope.size() > 0) {
				repositorioPerfilOperaciones.delete(ope.get(0));
			}

//			if(ope != null) {
//				repositorioPerfilOperaciones.deleteById(ope.getIdPerfilOperaciones());
//			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public List<PerfilOperaciones> onSearchPerfilOperationAsigned(int idPerfil, int idmenu) {
		List<PerfilOperaciones> lista = new ArrayList<>();
		try {
			List<PerfilOperaciones> menuid = repositorioPerfilOperaciones
					.findByFkPerfilIdPerfilAndFkMenuIdMenu(idPerfil, idmenu);
			if (menuid.size() > 0) {
				lista = repositorioPerfilOperaciones.findByFkPerfilIdPerfilAndFkMenuMenuIdPadre(idPerfil,
						menuid.get(0).getFkMenu().getMenuIdPadre());
			}

		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	@Transactional
	public PerfilOperaciones onBuscarPermidoRolMenu(int idmenu,Authentication auth) {
		
		PerfilOperaciones roles = new PerfilOperaciones();
		try {
			roles.setIdPerfilOperaciones(0);
			roles.setLeer(0);
			roles.setActualizar(0);
			roles.setCrear(0);
			roles.setEliminar(0);
			for (GrantedAuthority rol : auth.getAuthorities()) {
				ConstantApp.ROL_LOGIN = 	rol.getAuthority();
			}
			if (repositorioPerfilOperaciones.findByFkPerfilNombreAndFkMenuIdMenu(ConstantApp.ROL_LOGIN,
					idmenu) != null) {
				roles = repositorioPerfilOperaciones.findByFkPerfilNombreAndFkMenuIdMenu(ConstantApp.ROL_LOGIN, idmenu);
				
			}
		} catch (Exception e) {
			throw e;
		}

		return roles;
	}

}
