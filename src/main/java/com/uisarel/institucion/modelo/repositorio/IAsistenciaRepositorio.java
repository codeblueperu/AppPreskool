package com.uisarel.institucion.modelo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Asistencia;

@Repository
public interface IAsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {
	
	/**
	 * @author CodeBluePeru
	 * @param idcurso
	 * @param fecha
	 * @return
	 */
	List<Asistencia> findByCursoIdCursoAndEstudianteSeccionAlumnoIdSeccionAndFecha(int idcurso,int idseccion, Date fecha);
	
	/**
	 * @author CodeBluePeru
	 * @param fecha
	 * @param idestudiante
	 * @param idcurso
	 * @return
	 */
	Asistencia findByFechaAndEstudianteIdEstudianteAndCursoIdCurso(Date fecha, int idestudiante, int idcurso);
	
	/**
	 * @author CodeBluePeru
	 * @param estadoAsistencia
	 * @param idEstudiante
	 * @return
	 */
	List<Asistencia> findByEstadoAsistenciaAndEstudianteIdEstudiante(String estadoAsistencia,int idEstudiante);

}
