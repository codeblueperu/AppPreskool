package com.uisarel.institucion.servicio.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.modelo.repositorio.IUsuarioPerfilRepositorio;
import com.uisarel.institucion.modelo.repositorio.IUsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private IUsuarioRepositorio repoUser;

	@Autowired
	private IUsuarioPerfilRepositorio repoUserPerfil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = repoUser.findByUsuarioCorreo(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		List<UsuarioPerfil> listaroles = repoUserPerfil.findByFkUsuarioIdUsuario(user.getIdUsuario());

		Set<GrantedAuthority> listOfgrantedAuthorities = new HashSet<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(listaroles.get(0).getFkPerfil().getNombre());
		listOfgrantedAuthorities.add(grantedAuthority);

		//System.err.println(listaroles);

		String email = user.getUsuarioCorreo();
		String password = user.getContrasenia();

		return User.withUsername(email).password(password).authorities(grantedAuthority).build();
	}

}
