package com.uisarel.institucion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.repositorio.IUsuarioRepositorio;
import com.uisarel.institucion.servicio.IUsuarioServicio;
import com.uisarel.institucion.utils.ConstantApp;


@Service
@Component
public class UsuarioServicioImpl implements IUsuarioServicio{
	
	@Autowired
	private IUsuarioRepositorio repositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insertarUsuario(Usuario nuevoUsuario) {
		nuevoUsuario.setContrasenia(passwordEncoder.encode(nuevoUsuario.getContrasenia()));
		repositorio.save(nuevoUsuario);	
	}

	@Override
	public List<Usuario> listaUsuario() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public void actualizarUsuario(Usuario editado) {
		// TODO Auto-generated method stub
		repositorio.save(editado);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioId(int idUsuario) {
		// TODO Auto-generated method stub
		return repositorio.findById(idUsuario).get();
	}

	@Override
	@Transactional
	public void eliminarUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		repositorio.delete(buscarUsuarioId(idUsuario));
		
	}

	@Override
	public String updatePasswordUser(String password) {
		repositorio.onUpdateClaveUser(passwordEncoder.encode(password), ConstantApp.getuserLogin());
		return "La contraseña fue modificaca con éxito!";
	}

	
	//@Override
	//public List<Usuario> listarPorUsuario(String nombreUsuario) {
		// TODO Auto-generated method stub
	//	return repositorio.listarPorNombreUsuario(nombreUsuario);
	//}

}
