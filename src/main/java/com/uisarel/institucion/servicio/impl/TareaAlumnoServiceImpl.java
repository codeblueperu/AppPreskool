package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.TareaAlumno;
import com.uisarel.institucion.modelo.repositorio.ITareaAlumnoRepositorio;
import com.uisarel.institucion.servicio.ITareaAlumnoServicio;

@Service
@Transactional
public class TareaAlumnoServiceImpl implements ITareaAlumnoServicio {
	
	@Autowired
	private ITareaAlumnoRepositorio repoTareaAlumno;

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

}
