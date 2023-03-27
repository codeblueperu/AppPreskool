package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Usuario;

public interface IUsuarioServicio {
	
	public void insertarUsuario(Usuario nuevoUsuario);
	
	public List<Usuario> listaUsuario();
	
	public void actualizarUsuario(Usuario usuarioEditado);
	
	public Usuario buscarUsuarioId(int idUsuario);
	
	public void eliminarUsuario(int idUsuario);
	
	

}
