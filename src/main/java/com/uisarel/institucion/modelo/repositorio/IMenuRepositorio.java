package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Menu;

@Repository
@Component
public interface IMenuRepositorio extends JpaRepository<Menu, Integer> {
		
	@Query("Select m from Menu as m where m.fkPadre= null  ORDER BY m.orden ASC")
	public List<Menu> listarMenu();
	
	@Query("Select m from Menu as m where m.fkPadre != null  ORDER BY m.fkPadre ASC")
	public List<Menu> listarSubMenu();

}
