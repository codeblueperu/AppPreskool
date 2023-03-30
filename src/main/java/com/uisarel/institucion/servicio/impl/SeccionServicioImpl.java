package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.Seccion;
import com.uisarel.institucion.modelo.repositorio.ISeccionRepositorio;
import com.uisarel.institucion.servicio.ISeccionService;

@Service
@Transactional
public class SeccionServicioImpl implements ISeccionService {

	@Autowired
	private ISeccionRepositorio repoSeccion;

	@Override
	public List<Seccion> onListarSeccionAll() {
		List<Seccion> lista = new ArrayList<>();
		try {
			lista = repoSeccion.findAll();
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Seccion onGuardarSeccionNuevo(Seccion seccion) {
		Seccion response = new Seccion();
		try {
			response = repoSeccion.save(seccion);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Seccion onBuscarSeccionID(int idseccion) {
		Seccion response = new Seccion();
		try {
			Optional<Seccion> op = repoSeccion.findById(idseccion);

			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Seccion onUpdateSeccion(Seccion seccion) {
		Seccion response = new Seccion();
		try {
			response = repoSeccion.save(seccion);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarSeccionID(int idseccion) {
		try {
			repoSeccion.deleteById(idseccion);
		} catch (Exception e) {
			throw e;
		}
	}

}
