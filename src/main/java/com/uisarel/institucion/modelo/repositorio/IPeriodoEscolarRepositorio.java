package com.uisarel.institucion.modelo.repositorio;

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
}
