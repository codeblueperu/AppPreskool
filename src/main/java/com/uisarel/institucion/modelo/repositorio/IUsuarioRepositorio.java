package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Usuario;

import jakarta.transaction.Transactional;

@Repository
@Component
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	Usuario findByUsuarioCorreo(String username);
	
	/**
	 * @author SOPORTE
	 * @param documento
	 * @return
	 */
	Usuario findByCI(String documento);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario SET contrasenia = ?1 WHERE usuarioCorreo = ?2")
	void onUpdateClaveUser(String clave, String email);
	
}
