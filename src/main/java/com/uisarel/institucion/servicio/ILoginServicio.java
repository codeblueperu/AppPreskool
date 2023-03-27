package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Sesion;

public interface ILoginServicio {
	
	public void insertarLogin(Sesion nuevoLogin);
	
	public List<Sesion> listarLogin();

}
