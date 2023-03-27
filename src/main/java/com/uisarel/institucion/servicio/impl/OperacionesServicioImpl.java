package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.repositorio.IOperacionesRepositorio;
import com.uisarel.institucion.servicio.IOperacionesServicio;

@Service
@Component
public class OperacionesServicioImpl implements IOperacionesServicio {
	
	@Autowired
	private IOperacionesRepositorio repositorioOperaciones;

	/**
	 * CREATE
	 */
	
	@PreAuthorize("isAuthenticated() and (hasRole('ADMINISTRADOR') or hasRole('OTHERS'))")
	@Override
	public void insertarOperaciones(Operaciones nuevoOperaciones) {
		repositorioOperaciones.save(nuevoOperaciones);
		
	}

	@Override
	public List<Operaciones> listaOperaciones() {
		// TODO Auto-generated method stub
		return repositorioOperaciones.findAll();
	}

	@Override
	public void actualizarOperaciones(Operaciones operacionesEditado) {
		// TODO Auto-generated method stub
		repositorioOperaciones.save(operacionesEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public Operaciones buscarOperacionesId(int idOperaciones) {
		// TODO Auto-generated method stub
		return repositorioOperaciones.findById(idOperaciones).get();
	}

	@Override
	@Transactional
	public void eliminarOperaciones(int idOperaciones) {
		// TODO Auto-generated method stub
		repositorioOperaciones.delete(buscarOperacionesId(idOperaciones));
		
	}


}
