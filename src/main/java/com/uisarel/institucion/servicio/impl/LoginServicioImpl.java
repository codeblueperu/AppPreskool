package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Sesion;
import com.uisarel.institucion.modelo.repositorio.ILoginRepositorio;
import com.uisarel.institucion.servicio.ILoginServicio;

@Service
@Component
public class LoginServicioImpl implements ILoginServicio {

	@Autowired
	private ILoginRepositorio repositorio;
	
	@Override
	public void insertarLogin(Sesion nuevoLogin) {
		repositorio.save(nuevoLogin);
		
	}

	@Override
	public List<Sesion> listarLogin() {
		// TODO Auto-generated method stub
		return null;
	}

}
