package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;

public interface IUsuarioPerfilServicio {
	
	public void insertarUsuaPerf(UsuarioPerfil nuevoUsuarioPerfil);
	
	public List<UsuarioPerfil> listaUsuarioPerfil();
	
	public void actualizarUsuarioPerfil(UsuarioPerfil usuarioPerfilEditado);
	
	public UsuarioPerfil buscarUsuarioPerfilId(int idUsuarioPerfil);
	
	public void eliminarUsuarioPerfil(int idUsuarioPerfil);
	
}
