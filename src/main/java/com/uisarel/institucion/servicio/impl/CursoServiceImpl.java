package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Cursos;
import com.uisarel.institucion.modelo.repositorio.ICursosRepositorio;
import com.uisarel.institucion.servicio.ICursosService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoServiceImpl implements ICursosService {

	@Autowired
	private ICursosRepositorio repoCurso;

	@Override
	public List<Cursos> onListarCursos(String estado) {
		List<Cursos> lista = new ArrayList<>();
		try {
			lista = repoCurso.findAll();
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Cursos onGuardarCursoNuevo(Cursos datacurso) {
		Cursos response = null;
		try {
			response = repoCurso.save(datacurso);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Cursos onBuscarCursoID(int idcurso) {
		Cursos response = null;
		try {
			Optional<Cursos> op = repoCurso.findById(idcurso);

			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Cursos onUpdateCurso(Cursos updatedata) {
		Cursos response = null;
		try {
			response = repoCurso.save(updatedata);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarCurso(int idcurso) {
		try {
			repoCurso.deleteById(idcurso);
		} catch (Exception e) {
			throw e;
		}

	}

}
