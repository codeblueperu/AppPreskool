package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.modelo.repositorio.IUsuarioPerfilRepositorio;
import com.uisarel.institucion.servicio.IUsuarioPerfilServicio;

@Service
@Component
public class UsuarioPerfilServicioImpl implements IUsuarioPerfilServicio {
	
	@Autowired
	private IUsuarioPerfilRepositorio repositorio;

	@Override
	public void insertarUsuaPerf(UsuarioPerfil nuevoUsuarioPerfil) {
		// TODO Auto-generated method stub
		repositorio.save(nuevoUsuarioPerfil);
	}

	@Override
	public List<UsuarioPerfil> listaUsuarioPerfil() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}
	
	@Override
	public void actualizarUsuarioPerfil(UsuarioPerfil usuarioPerfilEditado) {
		// TODO Auto-generated method stub
		repositorio.save(usuarioPerfilEditado);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioPerfil buscarUsuarioPerfilId(int idUsuarioPerfil) {
		// TODO Auto-generated method stub
		return repositorio.findById(idUsuarioPerfil).get();
	}

	@Override
	@Transactional
	public void eliminarUsuarioPerfil(int idUsuarioPerfil) {
		// TODO Auto-generated method stub
		repositorio.delete(buscarUsuarioPerfilId(idUsuarioPerfil));
	}
	
}
