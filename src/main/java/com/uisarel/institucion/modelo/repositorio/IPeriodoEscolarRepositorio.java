package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;

@Repository
public interface IPeriodoEscolarRepositorio extends JpaRepository<PeriodoEscolar, Integer> {

	/**
	 * @author JMISERVER13
	 * @apiNote SELECT * FROM PeriodoEscolar ORDER BY idPeriodoEscolar DESC
	 * @return
	 */
	List<PeriodoEscolar> findByOrderByIdPeriodoEscolarDesc();
}
