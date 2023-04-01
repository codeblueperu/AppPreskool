package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.Exceptions.ConflictException;
import com.uisarel.institucion.modelo.entidades.Personal;
import com.uisarel.institucion.modelo.repositorio.IPersonalRepositorio;
import com.uisarel.institucion.servicio.IPersonalServicio;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonalServiceImpl implements IPersonalServicio {

	@Autowired
	private IPersonalRepositorio repoPersonal;

	@Override
	public List<Personal> onListarPersonalAll() {
		List<Personal> lista = new ArrayList<>();

		try {
			lista = repoPersonal.findAll();
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Personal onGuardarDatosPersonal(Personal data) {
		Personal response = new Personal();
		try {
			// VALIDAMOS QUE NO EXISTA EL NUMERO DE DOCUMENTO
			if (!repoPersonal.findByNumDocumento(data.getNumDocumento()).isEmpty()) {
				throw new ConflictException(
						"El numero de documento <b>" + data.getNumDocumento() + "</b> ya se encuentra registrado.");
			}

			response = repoPersonal.save(data);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Personal onBuscarPersonalId(int idPersonal) {
		Personal response = new Personal();
		try {
			Optional<Personal> op = repoPersonal.findById(idPersonal);

			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Personal onUpdateDataPersonal(Personal dataPersonal) {
		Personal response = new Personal();
		try {
			response = repoPersonal.save(dataPersonal);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarDataPersonalID(int idpersonal) {
		try {
			repoPersonal.deleteById(idpersonal);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Personal onBuscarPersonalGradoSeccionCursoId(int idPersonal) {
		Personal response = new Personal();
		try {
			Optional<Personal> op = repoPersonal.findById(idPersonal);

			if (op.isPresent()) {
				response = op.get();
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
