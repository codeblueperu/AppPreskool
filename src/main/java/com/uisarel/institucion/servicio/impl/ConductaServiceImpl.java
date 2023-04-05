package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.Conducta;
import com.uisarel.institucion.modelo.repositorio.IConductaRepositorio;
import com.uisarel.institucion.servicio.IConductaService;
import com.uisarel.institucion.utils.ConstantApp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConductaServiceImpl implements IConductaService {

	@Autowired
	private IConductaRepositorio repoConducta;

	@Override
	public List<Conducta> onListarConductaEstudiante(int idestudiante) {
		List<Conducta> lista = new ArrayList<>();
		try {
			lista = repoConducta.findByEstudianteIdEstudiante(idestudiante);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Conducta onGuardarConducta(Conducta data) {
		Conducta response = new Conducta();
		try {
			response = repoConducta.save(data);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public List<Conducta> onBuscarConductaAlumnoID(int idconducta, int idcurso, int iddocente) {
		List<Conducta> response = new ArrayList<>();
		try {
			if(ConstantApp.ROL_LOGIN.compareTo("ADMINISTRADOR") == 0) {
				response = repoConducta.findByEstudianteIdEstudiante(idconducta);
			}else {
				response = repoConducta.findByEstudianteIdEstudianteAndCursoIdCursoAndPersonalIdPersonal(idconducta,idcurso,iddocente);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Conducta onUpdateConductaEstudiante(Conducta dataUpdate) {
		Conducta response = new Conducta();
		try {
			response = repoConducta.save(dataUpdate);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public void onEliminarConducta(int idConducta) {
		try {
			repoConducta.deleteById(idConducta);
		} catch (Exception e) {
			throw e;
		}

	}

}
