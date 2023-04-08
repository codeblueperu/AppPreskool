package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.modelo.entidades.AdminTemplate;
import com.uisarel.institucion.modelo.entidades.Conducta;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.repositorio.IConductaRepositorio;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IConductaService;
import com.uisarel.institucion.servicio.INotificacionesService;
import com.uisarel.institucion.utils.ConstantApp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConductaServiceImpl implements IConductaService {

	@Autowired
	private IConductaRepositorio repoConducta;
	
	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeridoEscolar;
	
	@Autowired
	private INotificacionesService srvNotifications;

	@Autowired
	private IAdminTemplateService srvTemplate;

	@Override
	public List<Conducta> onListarConductaEstudiante(int idestudiante) {
		List<Conducta> lista = new ArrayList<>();
		try {
			if(ConstantApp.getuRolUser().compareTo("ESTUDIANTE") == 0) {
//				PERIODO ESCOLAR
				PeriodoEscolar periodo = repoPeridoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
//				DATOS DEL ESTUDIANTE LOGUEADO
				Estudiante studentLogin = repoEstudiante.findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(
						ConstantApp.getuserLogin(), periodo.getIdPeriodoEscolar());
				lista = repoConducta.findByEstudianteIdEstudiante(studentLogin.getIdEstudiante());
				
			}else {
				lista = repoConducta.findByEstudianteIdEstudiante(idestudiante);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Conducta onGuardarConducta(Conducta data) {
		Conducta dt = new Conducta();
		try {
			dt = repoConducta.save(data);
			
			Estudiante response = repoEstudiante.findById(dt.getEstudiante().getIdEstudiante()).get();
			if(dt.getNotificarAPoderadoEmail().compareTo("SI") == 0) {
				//System.err.println(response);
//				SERVICE TEMPLATE
				AdminTemplate template = srvTemplate.onMostrarDataTemplateAdmin();				
//				PREPARE TEXT SEND 
				String charreao = "<div style='width:90%;padding:.3rem;border-left:4px solid #3d5ee1;font-family: Courier, monospace;'> <h1>¡Reporte escolar! "
						+ "  </h1>" + "<br>Estimado <b> sñr(a) " + response.getApoderadoEstudiante().getNombre() +" " + response.getApoderadoEstudiante().getApmaterno()
						+ "</b>,<br> <p style='text-align:justify;'>"
						+ dt.getDescripcion()
						+ "</p></div>";
//				SEND EMAIL
				srvNotifications.sendMensajeChasqui(
						"Reporte de conducta de la escuela " + template.getNameSistema(),
						charreao, response.getApoderadoEstudiante().getEmail(), false, null);
			}
		} catch (Exception e) {
			throw e;
		}
		return dt;
	}

	@Override
	public List<Conducta> onBuscarConductaAlumnoID(int idconducta, int idcurso, int iddocente) {
		List<Conducta> response = new ArrayList<>();
		try {
			if(ConstantApp.getuRolUser().compareTo("ADMINISTRADOR") == 0) {
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
