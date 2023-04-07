package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.entidades.TareaAlumno;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.modelo.repositorio.ITareaAlumnoRepositorio;
import com.uisarel.institucion.servicio.ITareaAlumnoServicio;
import com.uisarel.institucion.utils.ConstantApp;

@Service
@Transactional
public class TareaAlumnoServiceImpl implements ITareaAlumnoServicio {
	
	@Autowired
	private ITareaAlumnoRepositorio repoTareaAlumno;
	
	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeridoEscolar;

	@Override
	public List<TareaAlumno> onBuscarTareaAlumno(int idGrado, int idSeccion, int idcurso, int idtarea) {
		List<TareaAlumno> lista = new ArrayList<>();
		try {
			lista = repoTareaAlumno.findByTareaIdTareaAndTareaGradoIdGradoAndTareaSeccionIdSeccionAndTareaCursoIdCurso(idtarea,idGrado, idSeccion, idcurso);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public TareaAlumno onGuardarTareaALumno(TareaAlumno datos) {
		TareaAlumno tarea = new TareaAlumno();
		try {
			//ELIMINAMOS PARA EVITAR DUPLICADOS 
			onEliminarTareaAlumno(datos.getTarea().getIdTarea(), datos.getAlumno().getIdEstudiante());
			tarea = repoTareaAlumno.save(datos);
		} catch (Exception e) {
			throw e;
		}
		return tarea;
	}

	@Override
	public void onEliminarTareaAlumno(int idtareaAlumno, int idalumno) {
		try {
			repoTareaAlumno.onFindByEliminarTareaALumno(idtareaAlumno, idalumno);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<TareaAlumno> onDataDashBoardStudentTask(int idEstudiante,String estadoPresenta) {
		List<TareaAlumno> lista = new ArrayList<>();
		try {
//			PERIODO ESCOLAR
			PeriodoEscolar periodo = repoPeridoEscolar.findByEstado("APERTURADO").get(0);
//			DATOS DEL ESTUDIANTE LOGUEADO
			Estudiante studentLogin = repoEstudiante.findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(
					ConstantApp.getuserLogin(), periodo.getIdPeriodoEscolar());
//			LISTA DATA
			lista = repoTareaAlumno.findByEstadoPresentaAndAlumnoIdEstudiante(estadoPresenta, studentLogin.getIdEstudiante());
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

}
