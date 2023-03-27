package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.repositorio.IPerfilRepositorio;
import com.uisarel.institucion.servicio.IPerfilServicio;

@Service
@Component
public class PerfilServicioImpl implements IPerfilServicio{
	
	@Autowired
	private IPerfilRepositorio repositorio;

	@Override
	@Transactional
	public void insertarPerfil(Perfil nuevoPerfil) {
		repositorio.save(nuevoPerfil);
		
	}

	@Override
	public List<Perfil> listaPerfil() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public void actualizarPerfil(Perfil perfilEditado) {
		// TODO Auto-generated method stub
		repositorio.save(perfilEditado);
	}

	@Override
	@Transactional(readOnly = true)
	public Perfil buscarPerfilId(int idPerfil) {
		// TODO Auto-generated method stub
		return repositorio.findById(idPerfil).get();
	}

	@Override
	public void eliminarPerfil(int idPerfil) {
		// TODO Auto-generated method stub
		repositorio.delete(buscarPerfilId(idPerfil));
		
	}

}
