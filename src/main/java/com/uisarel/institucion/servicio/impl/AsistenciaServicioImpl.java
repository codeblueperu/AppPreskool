package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Asistencia;
import com.uisarel.institucion.modelo.repositorio.IAsistenciaRepositorio;
import com.uisarel.institucion.servicio.IAsistenciaServicio;

@Service
@Transactional
public class AsistenciaServicioImpl implements IAsistenciaServicio {

	@Autowired
	private IAsistenciaRepositorio repoAsistencia;

	@Override
	public List<Asistencia> onListarAsistenciaCursoFecha(int idcurso, int idseccion, Date fecha) {
		List<Asistencia> asistencia = new ArrayList<>();
		try {
			asistencia = repoAsistencia.findByCursoIdCursoAndEstudianteSeccionAlumnoIdSeccionAndFecha(idcurso,idseccion,fecha);
		} catch (Exception e) {
			throw e;
		}
		return asistencia;
	}

	@Override
	public Asistencia onBuscarAsistenciaCursoFechaId(int idasistencia) {
		Asistencia data = new Asistencia();
		try {
			Optional<Asistencia> op = repoAsistencia.findById(idasistencia);

			if (op.isPresent()) {
				data = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return data;
	}

	@Override
	public Asistencia onGuardarAsistenciaCursoFecha(Asistencia data) {
		Asistencia response = null;
		try {
			// ANTES DE GUARDAR ELIMINAMOS PARA EVITAR DUPLICADOS
			response = repoAsistencia.findByFechaAndEstudianteIdEstudianteAndCursoIdCurso(data.getFecha(),
					data.getEstudiante().getIdEstudiante(), data.getCurso().getIdCurso());
			if (response != null) {
				onEliminarAsistenciaCursoFecha(response.getIdAsistencia());
			}

			// GUARDAMOS NUEVO
			response = repoAsistencia.save(data);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarAsistenciaCursoFecha(int idasistencia) {

		try {
			repoAsistencia.deleteById(idasistencia);
		} catch (Exception e) {
			throw e;
		}

	}

}
