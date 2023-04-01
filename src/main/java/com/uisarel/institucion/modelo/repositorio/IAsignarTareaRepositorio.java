package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;

@Repository
public interface IAsignarTareaRepositorio extends JpaRepository<AsignarTarea, Integer> {

	/**
	 * @author JMISERVER13
	 * @apiNote SELECT * FROM AsignarTarea WHERE idPeriodoEscolar = ?1
	 * @param idPeriodoEscolar
	 * @return
	 */
	List<AsignarTarea> findByPeriodoEscolarIdPeriodoEscolar(int idPeriodoEscolar);
	
}