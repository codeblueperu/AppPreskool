package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.modelo.repositorio.IPerfilOperacionesRepositorio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;

@Service
@Component
public class PerfilOperacionesSevicioImpl implements IPerfilOperacionesServicio {

	@Autowired
	public IPerfilOperacionesRepositorio repositorioPerfilOperaciones;

	@Override
	@Transactional
	public void insertarPefilOperaciones(PerfilOperaciones lstPermisos) {

		PerfilOperaciones deleteForm = repositorioPerfilOperaciones.findByFkPerfilIdPerfilAndFkOperacionesIdOperaciones(
				lstPermisos.getFkPerfil().getIdPerfil(), lstPermisos.getFkOperaciones().getIdOperaciones());
		if (deleteForm != null) {
			repositorioPerfilOperaciones.delete(deleteForm);
		}

		repositorioPerfilOperaciones.save(lstPermisos);
	}

	@Override
	public List<PerfilOperaciones> listaPefilOperaciones() {

		return repositorioPerfilOperaciones.findAll();
	}

	@Override
	public void actualizarPerfilOperaciones(PerfilOperaciones perfilOperacionesEditado) {

		repositorioPerfilOperaciones.save(perfilOperacionesEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public PerfilOperaciones buscarPerfilOperacionesId(int idPerfilOperaciones) {

		return repositorioPerfilOperaciones.findById(idPerfilOperaciones).get();
	}

	@Override
	@Transactional
	public void eliminarPerfilOperaciones(int idPerfilOperaciones) {

		repositorioPerfilOperaciones.delete(buscarPerfilOperacionesId(idPerfilOperaciones));
	}

	// CONTROL DATOS DUPLICADOS
	@Override
	public PerfilOperaciones PerfilOperacionesFkPerfil(Perfil fkPerfil) {

		return repositorioPerfilOperaciones.findByFkPerfil(fkPerfil);
	}

	@Override
	public PerfilOperaciones PerfilOperacionesFkOperaciones(Operaciones fkOperaciones) {

		return repositorioPerfilOperaciones.findByFkOperaciones(fkOperaciones);
	}

	@Override
	public List<PerfilOperaciones> onBuscarPerfilOperacionAsinados(int idperfil) {
		// TODO Auto-generated method stub
		return repositorioPerfilOperaciones.findByFkPerfilIdPerfil(idperfil);
	}

}
