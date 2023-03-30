package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;

@Repository
public interface IPeriodoEscolarRepositorio extends JpaRepository<PeriodoEscolar, Integer> {

	/**
	 * @author JMISERVER13
	 * @apiNote SELECT * FROM PeriodoEscolar where AnioEscolar = ?1
	 * @return
	 */
	PeriodoEscolar findByAnioEscolar(int anio);
	
	/**
	 * @author JMISERVER13
	 * @apiNote SELECT * FROM PeriodoEscolar where Estado = ?1
	 * @param estado
	 * @return
	 */
	List<PeriodoEscolar> findByEstado(String estado);
}
