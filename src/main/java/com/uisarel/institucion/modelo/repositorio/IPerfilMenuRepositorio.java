package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.PerfilMenu;

@Repository
public interface IPerfilMenuRepositorio extends JpaRepository<PerfilMenu, Integer> {
	
	/**
	 * @author JMISERVER13
	 * @param idperfil
	 * @param idmenu
	 * @return
	 */
	PerfilMenu findByFkPerfilIdPerfilAndFkMenuIdMenu(int idperfil, int idmenu);
	
	/**
	 * @author JMISERVER13
	 * @param idperfil
	 * @param menuGrupo
	 * @return
	 */
	List<PerfilMenu> findByFkPerfilIdPerfilAndFkMenuMenuIdPadre(int idperfil, String menuGrupo);
	
	/**
	 * @author JMISERVER13
	 * @param namePerfil
	 * @param menuPrincipal
	 * @return
	 */
	List<PerfilMenu> findByFkPerfilNombreAndFkMenuOrden(String namePerfil, String menuPrincipal);
	
	/**
	 * @author JMISERVER13
	 * @param namePerfil
	 * @param grupoMenu
	 * @param submenu
	 * @return
	 */
	List<PerfilMenu> findByFkPerfilNombreAndFkMenuMenuIdPadreAndFkMenuOrden(String namePerfil, String grupoMenu, String submenu);
	
	
	
}
