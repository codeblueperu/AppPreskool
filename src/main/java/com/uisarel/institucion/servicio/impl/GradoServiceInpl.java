package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Grado;
import com.uisarel.institucion.modelo.repositorio.IGradoRepositorio;
import com.uisarel.institucion.servicio.IGradoService;

@Service
@Transactional
public class GradoServiceInpl implements IGradoService {

	@Autowired
	private IGradoRepositorio repoGrado;

	@Override
	public List<Grado> onListarGradosAll() {
		List<Grado> lista = new ArrayList<>();
		try {
			lista = repoGrado.findAll();
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Grado onGuardarGradoNuevo(Grado grado) {
		Grado response = new Grado();
		try {
			response = repoGrado.save(grado);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Grado onBuscarGradoId(int idgrado) {
		Grado response = new Grado();
		try {
			Optional<Grado> op = repoGrado.findById(idgrado);
			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Grado onUpdateGrado(Grado grado) {
		Grado response = new Grado();
		try {
			response = repoGrado.save(grado);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarGrado(int idgrado) {

		try {
			repoGrado.deleteById(idgrado);
		} catch (Exception e) {
			throw e;
		}

	}

}
