package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.utils.ConstantApp;

@Service
@Transactional
public class PeriodoEscolarServiceImpl implements IPeriodoEscolarService {

	@Autowired
	private IPeriodoEscolarRepositorio repoPerido;

	@Override
	public List<PeriodoEscolar> onListarPeriodoEscolarAll() {
		List<PeriodoEscolar> dtData = new ArrayList<>();
		try {
			dtData = repoPerido.findAll();
		} catch (Exception e) {
			System.err.println("Error al listar periodo escolar");
			throw e;
		}
		return dtData;
	}

	@Override
	public PeriodoEscolar onGuardarNuevoPeriodoEscolar(PeriodoEscolar dataperiodo) {
		PeriodoEscolar newdata = null;
		try {
			if(repoPerido.findByAnioEscolar(dataperiodo.getAnioEscolar()) == null) {
				newdata = repoPerido.save(dataperiodo);
			}
			
		} catch (Exception e) {
			System.err.println("Error al guardar periodo escolar");
			throw e;
		}
		return newdata;
	}

	@Override
	public PeriodoEscolar onBuscarPeriodoEscolarID(int idPeriodo) {
		PeriodoEscolar newdata = new PeriodoEscolar();
		try {
			Optional<PeriodoEscolar> getOn = repoPerido.findById(idPeriodo);

			if (getOn.isPresent()) {
				newdata = getOn.get();
			}
		} catch (Exception e) {
			System.err.println("Error al buscarID periodo escolar");
			throw e;
		}
		return newdata;
	}

	@Override
	public PeriodoEscolar onUpdatePeriodoEscolar(PeriodoEscolar updateData) {
		PeriodoEscolar newdata = new PeriodoEscolar();
		try {
			if (repoPerido.existsById(updateData.getIdPeriodoEscolar())) {
				newdata = repoPerido.save(updateData);
			}

		} catch (Exception e) {
			System.err.println("Error al actualizar periodo escolar");
			throw e;
		}
		return newdata;
	}

	@Override
	public void onEliminarPeriodoEscolar(int idperiodo) {
		try {
			if (repoPerido.existsById(idperiodo)) {
				repoPerido.deleteById(idperiodo);
			}

		} catch (Exception e) {
			System.err.println("Error al eliminar periodo escolar");
			throw e;
		}

	}

	@Override
	public List<PeriodoEscolar> onListarPeriodoEscolarEstado(String estado) {
		List<PeriodoEscolar> dtData = new ArrayList<>();
		try {
			if(ConstantApp.ROL_LOGIN.compareTo("ADMINISTRADOR") == 0) {
				dtData = repoPerido.findAll();
			}else {
				dtData = repoPerido.findByEstado("APERTURADO");
			}
			
		} catch (Exception e) {
			System.err.println("Error al listar periodo escolar");
			throw e;
		}
		return dtData;
	}

}
