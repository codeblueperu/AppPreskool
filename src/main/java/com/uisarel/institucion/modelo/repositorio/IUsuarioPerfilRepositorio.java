package com.uisarel.institucion.modelo.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;

@Repository
@Component
public interface IUsuarioPerfilRepositorio extends JpaRepository<UsuarioPerfil, Integer>{
	
	/**
	 * @author JMISERVER13
	 * @param idUser
	 * @return
	 */
	List<UsuarioPerfil> findByFkUsuarioIdUsuario(int idUser);
	
	@Query("Select up from UsuarioPerfil up where up.estado= ?1")
	public List<UsuarioPerfil> listaUsuarioPerfil(String nombreUP);
	

	
}
