package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.Exceptions.ConflictException;
import com.uisarel.institucion.Exceptions.NotFoundException;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.servicio.IEstudianteService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeridoEscolar;

	@Override
	public List<Estudiante> onListarEstuandePeriodoEscolar(int periodoEscolar) {

		return repoEstudiante.findAll();
	}

	@Override
	public Estudiante onGuardarEstudianteNuevo(Estudiante dataEstudiante) {
		Estudiante response = null;
		try {
			// VALIDAMOS QUE EL PERIODO ESTE ACTIVO
			Optional<PeriodoEscolar> validatePeriodo = repoPeridoEscolar
					.findById(dataEstudiante.getPeriodoEscolar().getIdPeriodoEscolar());
			if (validatePeriodo.isPresent()) {
				if (validatePeriodo.get().getEstado().compareTo("CLAUSURADO") != 0) {
					// VALIDAMOS QUE NO EXISTA UN REGISTRO CON EL MISMO DOCUMENTO Y PERIODO ESCOLAR
					Estudiante existe = repoEstudiante.findByNumDocumentoAndPeriodoEscolarIdPeriodoEscolar(
							dataEstudiante.getNumDocumento(), dataEstudiante.getPeriodoEscolar().getIdPeriodoEscolar());
					// SI NO EXISTE GUARDAMOS
					if (existe == null) {
						response = repoEstudiante.save(dataEstudiante);
					} else {
						throw new ConflictException("El estudiante <b>" + dataEstudiante.getNombreEstudiante() + " "
								+ dataEstudiante.getApPaterno()
								+ "</b>, ya se encuentra inscrito en el periodo escolar AF-"
								+ validatePeriodo.get().getAnioEscolar());
					}
				} else {
					throw new ConflictException("El periodo escolar AF-" + validatePeriodo.get().getAnioEscolar()
							+ " se encuentra <b>CLAUSURADO</b>.");
				}
			} else {
				throw new NotFoundException("No se encontro ningun periodo escolar con el parametro enviado.");
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Estudiante onBuscarEstudianteId(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estudiante onUpdateEstudiantePeriodo(Estudiante dataUpdate) {
		Estudiante response = new Estudiante();
		try {
			response = repoEstudiante.save(dataUpdate);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarEstudiantePeriodoId(int idEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estudiante> onListarEstuandePeriodoEscolarGradoSeccionNivel(int periodoEscolar, String nivelEscolar,
			int idGrado, int idSeccion) {
		List<Estudiante> lista = new ArrayList<>();
		try {
			lista = repoEstudiante
					.findByPeriodoEscolarIdPeriodoEscolarAndNivelEscolarAndGradoAlumnoIdGradoAndSeccionAlumnoIdSeccion(
							periodoEscolar, nivelEscolar, idGrado, idSeccion);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Estudiante onBuscarEstudiantePeriodoEscolarId(int idEstudiante, int idperiodoEscolar) {
		Estudiante response = new Estudiante();
		try {
			response = repoEstudiante.findByPeriodoEscolarIdPeriodoEscolarAndIdEstudiante(idperiodoEscolar, idEstudiante);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
