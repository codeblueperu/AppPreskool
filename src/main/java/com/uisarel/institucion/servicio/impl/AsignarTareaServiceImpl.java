package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.repositorio.IAsignarTareaRepositorio;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.servicio.IAsignarTareaServicio;
import com.uisarel.institucion.utils.ConstantApp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AsignarTareaServiceImpl implements IAsignarTareaServicio {

	@Autowired
	private IAsignarTareaRepositorio repoAsignarTarea;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeriodoEscolar;

	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Override
	public List<AsignarTarea> onListarTareaPeriodoEscolarAperturado(int idPeriodoEscolar) {
		List<AsignarTarea> lista = new ArrayList<>();
		try {
//			OBTENER EL PERIODO ESCOLAR ACTUAL
			PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
			if (ConstantApp.getuRolUser().compareTo("ADMINISTRADOR") == 0) {
				lista = repoAsignarTarea.findByPeriodoEscolarIdPeriodoEscolarOrderByFechaPresentacionDesc(periodo.getIdPeriodoEscolar());
			} else if (ConstantApp.getuRolUser().compareTo("ESTUDIANTE") == 0) {
//				BUSCAR DATOS DEL ALUMNO
				Estudiante studentLogin = repoEstudiante.findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(
						ConstantApp.getuserLogin(), periodo.getIdPeriodoEscolar());
//				OBTENER TODA LAS TAREAS DEL ALUMNO LOGUEADO ES DECIR POR GRADO Y SECCION
				lista = repoAsignarTarea.findByPeriodoEscolarIdPeriodoEscolarAndGradoIdGradoAndSeccionIdSeccion(
						periodo.getIdPeriodoEscolar(), studentLogin.getGradoAlumno().getIdGrado(),
						studentLogin.getSeccionAlumno().getIdSeccion());
			} else {
				/**
				 * @author DOCENTE PROFILES
				 */
				lista = repoAsignarTarea.findByPeriodoEscolarIdPeriodoEscolarAndPersonalEmail(
						periodo.getIdPeriodoEscolar(), ConstantApp.getuserLogin());
			}

		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public AsignarTarea onGuardarTareaNuevaPeriodoEscolar(AsignarTarea datos) {
		AsignarTarea response = new AsignarTarea();
		try {
			PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
			datos.setFechaAsignacion(new Date());
			datos.setPeriodoEscolar(periodo);
			response = repoAsignarTarea.save(datos);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public AsignarTarea onBuscarTareaIdPeriodoAprturado(int idTarea) {
		AsignarTarea response = new AsignarTarea();
		try {
			Optional<AsignarTarea> op = repoAsignarTarea.findById(idTarea);

			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public AsignarTarea onUpdateTareaPeriodoEscolar(AsignarTarea datosupdate) {
		AsignarTarea response = new AsignarTarea();
		try {
			response = repoAsignarTarea.save(datosupdate);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarTareaIdPeridoEscolar(int idtarea) {
		try {
			repoAsignarTarea.deleteById(idtarea);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<AsignarTarea> onListarTareaPeriodoEscolarAperturadoDocenteCursoGradoSeccion(int idDocente, int idcurso,
			int idgrado, int idseccion) {
		List<AsignarTarea> lista = new ArrayList<>();
		try {
			PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);

			lista = repoAsignarTarea
					.findByPeriodoEscolarIdPeriodoEscolarAndPersonalIdPersonalAndCursoIdCursoAndGradoIdGradoAndSeccionIdSeccion(
							periodo.getIdPeriodoEscolar(), idDocente, idcurso, idgrado, idseccion);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public List<AsignarTarea> onListarTareasActivas(int idGrado, int idseccion, String nivelEscolar,
			Date fechaVigente) {
		List<AsignarTarea> lista = new ArrayList<>();
		try {
//			PERIODO ESCOLAR APERTURADO
			PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
//			DATOS DEL USUARIO LOGUEADO
			Estudiante studentLogin = repoEstudiante.findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(
					ConstantApp.getuserLogin(), periodo.getIdPeriodoEscolar());
//			DATA TASK RETURN
			lista = repoAsignarTarea.findByNivelEscolarAndGradoIdGradoAndSeccionIdSeccionAndFechaPresentacionAfter(
					studentLogin.getNivelEscolar(), studentLogin.getGradoAlumno().getIdGrado(),
					studentLogin.getSeccionAlumno().getIdSeccion(), new Date());

		} catch (Exception e) {
			throw e;
		}
		return lista;
	}


}
