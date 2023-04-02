package com.uisarel.institucion.modelo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.TareaAlumno;

import jakarta.transaction.Transactional;

@Repository
public interface ITareaAlumnoRepositorio extends JpaRepository<TareaAlumno, Integer> {
	
	/**
	 * @author JMISERVER13
	 * @param idGrado
	 * @param idSeccion
	 * @param idcurso
	 * @return
	 */
	List<TareaAlumno> findByTareaIdTareaAndTareaGradoIdGradoAndTareaSeccionIdSeccionAndTareaCursoIdCurso(int idtarea, int idGrado, int idSeccion, int idcurso);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM TareaAlumno  WHERE tarea.idTarea = ?1 AND alumno.idEstudiante = ?2")
	int onFindByEliminarTareaALumno(int idtarea, int idalumno);

}
