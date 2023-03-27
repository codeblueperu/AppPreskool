package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Menu;

@Repository
public interface IMenuRepositorio extends JpaRepository<Menu, Integer> {
	
	/**
	 * @author JMISERVER13
	 * @apiNote LISTAR DE MENU Y SUBGRUPOS SEGUN PERFILES Y ROLES
	 * @param menuGrupo
	 * @param menuPrincipal
	 * @return
	 */
	List<Menu> findByMenuIdPadreAndOrdenAndEstadoMenu(String menuGrupo, String menuPrincipal,String estadoMenu);
	
	/**
	 * @author JMISERVER13
	 * @param menuPrincipal
	 * @param estadoMenu
	 * @return
	 */
	List<Menu> findByOrdenAndEstadoMenuOrderByMenuIdPadreAsc(String menuPrincipal,String estadoMenu);

}
