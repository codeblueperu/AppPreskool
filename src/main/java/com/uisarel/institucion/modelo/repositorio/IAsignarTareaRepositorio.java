package com.uisarel.institucion.modelo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;

@Repository
public interface IAsignarTareaRepositorio extends JpaRepository<AsignarTarea, Integer> {

	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FROM AsignarTarea WHERE idPeriodoEscolar = ?1
	 * @param idPeriodoEscolar
	 * @return
	 */
	List<AsignarTarea> findByPeriodoEscolarIdPeriodoEscolar(int idPeriodoEscolar);
	
	/**
	 * @author CodeBluePeru
	 * @param idPeriodoEscolar
	 * @param idgrado
	 * @param idseccion
	 * @return
	 */
	List<AsignarTarea> findByPeriodoEscolarIdPeriodoEscolarAndGradoIdGradoAndSeccionIdSeccion(int idPeriodoEscolar,int idgrado, int idseccion);
	/**
	 * @author CodeBluePeru
	 * @param idPeriodoEscolar
	 * @param personalLogin
	 * @return
	 */
	List<AsignarTarea> findByPeriodoEscolarIdPeriodoEscolarAndPersonalEmail(int idPeriodoEscolar,String personalLogin);

	/**
	 * @author CodeBluePeru
	 * @apiNote SELECT * FROM AsignarTarea WHERE idPeriodoEscolar = ?1 AND
	 *          idPersonal = ?2 AND idCurso = ?3 AND idGrado = ?4 AND idSeccion = ?
	 *          5
	 * @param idPeriodoEscolar
	 * @param idpersonal
	 * @param idcurso
	 * @param idgrado
	 * @param idseccion
	 * @return
	 */
	List<AsignarTarea> findByPeriodoEscolarIdPeriodoEscolarAndPersonalIdPersonalAndCursoIdCursoAndGradoIdGradoAndSeccionIdSeccion(
			int idPeriodoEscolar, int idpersonal, int idcurso, int idgrado, int idseccion);
	
	/**
	 * @author CodeBluePeru
	 * @param nivelEscolar
	 * @param idGrado
	 * @param idSeccion
	 * @param fechaPresentacion
	 * @return
	 */
	List<AsignarTarea> findByNivelEscolarAndGradoIdGradoAndSeccionIdSeccionAndFechaPresentacionAfter(String nivelEscolar, int idGrado, int idSeccion,Date fechaPresentacion);

}
