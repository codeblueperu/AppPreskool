package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Perfil;

public interface IPerfilServicio {

	public void insertarPerfil(Perfil nuevoPerfil);

	public List<Perfil> listaPerfil();
	
	public void actualizarPerfil(Perfil perfilEditado);
	
	public Perfil buscarPerfilId(int idPerfil);
	
	public void eliminarPerfil(int idPerfil);

}
