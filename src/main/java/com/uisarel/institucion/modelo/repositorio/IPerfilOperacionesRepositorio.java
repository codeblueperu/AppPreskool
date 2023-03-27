package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;

@Repository
@Component
public interface IPerfilOperacionesRepositorio extends JpaRepository<PerfilOperaciones, Integer>{

	/**
	 * @author JMISERVER13
	 * @param idperfil
	 * @param idoperation
	 * @return
	 */
	PerfilOperaciones findByFkPerfilNombreAndFkMenuIdMenu(String perfil, int idmenu);
	
	
	/**
	 * @author JMISERVER13
	 * @param idperfil
	 * @return
	 */
	List<PerfilOperaciones> findByFkPerfilIdPerfilAndFkMenuIdMenu(int idperfil,int idMenu);
	
	/**
	 * @author JMISERVER13
	 * @param idperfil
	 * @param grupoMenu
	 * @return
	 */
	List<PerfilOperaciones> findByFkPerfilIdPerfilAndFkMenuMenuIdPadre(int idperfil,String grupoMenu);
}
