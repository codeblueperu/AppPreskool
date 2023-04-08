package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.Exceptions.ConflictException;
import com.uisarel.institucion.modelo.entidades.AdminTemplate;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.entidades.Personal;
import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.modelo.repositorio.IPersonalRepositorio;
import com.uisarel.institucion.modelo.repositorio.IUsuarioPerfilRepositorio;
import com.uisarel.institucion.modelo.repositorio.IUsuarioRepositorio;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.INotificacionesService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.utils.ConstantApp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonalServiceImpl implements IPersonalServicio {

	@Autowired
	private IPersonalRepositorio repoPersonal;

	@Autowired
	private IUsuarioRepositorio repoUser;

	@Autowired
	private IUsuarioPerfilRepositorio repoPerfil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeridoEscolar;

	@Autowired
	private INotificacionesService srvNotifications;

	@Autowired
	private IAdminTemplateService srvTemplate;

	@Override
	public List<Personal> onListarPersonalAll() {
		List<Personal> lista = new ArrayList<>();

		try {
			if (ConstantApp.getuRolUser().compareTo("ADMINISTRADOR") == 0) {
				lista = repoPersonal.findAll();
			} else {
				lista = repoPersonal.findByEmail(ConstantApp.getuserLogin());
			}

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

//			CREAR DOCENTE
			response = repoPersonal.save(data);

//			BUSCAR CUENTA USUARIO DOCENTE
			Usuario user = new Usuario();
			Usuario exists = repoUser.findByCI(response.getNumDocumento().trim());
			if (exists != null) {
				user.setIdUsuario(exists.getIdUsuario());
			}

//				CREAR CUENTA DE USUARIO	DOCENTE						
			user.setApellidos(response.getApellidos());
			user.setNombres(response.getNombre());
			user.setCI(response.getNumDocumento());
			user.setDireccion(response.getDireccion());
			user.setEstadoU("Activo");
			user.setUsuarioCorreo(response.getEmail().toLowerCase());
			user.setFechaNacimiento(response.getFechaNacimiento());
			user.setSexo(response.getSexo());
			user.setContrasenia(passwordEncoder.encode(response.getNumDocumento()));

			String nameTeacher = response.getApellidos() + " " + response.getNombre();
			user = repoUser.save(user);
			if (exists == null) {
				UsuarioPerfil perfilUser = new UsuarioPerfil();
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(4);
				perfilUser.setEstado("Activo");
				perfilUser.setFkUsuario(user);
				perfilUser.setFechaCreacionPerfil(new Date());
				perfilUser.setFkPerfil(perfil);
				repoPerfil.save(perfilUser);

//				SERVICE TEMPLATE
				AdminTemplate template = srvTemplate.onMostrarDataTemplateAdmin();
				PeriodoEscolar periodo = repoPeridoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);

//				PREPARE TEXT SEND 
				String charreao = "<div style='width:90%;padding:.3rem;border-left:4px solid #3d5ee1;font-family: Courier, monospace;'> <h1>¡Hola! Bienvenid@ a "
						+ template.getNameSistema() + "  </h1>" + "<br>Estimado <b>" + nameTeacher
						+ "</b>, <br> se realizo la creacion de su cuenta a la plataforma escolar" + " para el AF-"
						+ periodo.getAnioEscolar() + ".<br> sus credenciales de acceso son:<br><br>"
						+ "<div><b>Usuario:</b> " + response.getEmail().toLowerCase() + "<br>" + "<b>Contraseña:</b> "
						+ response.getNumDocumento() + "<br><b>Link:</b> <a href='" + template.getLinkAcceso() + "'> "
						+ template.getLinkAcceso().toLowerCase() + "</a> </div>" + "</div>";
//				SEND EMAIL
				srvNotifications.sendMensajeChasqui(
						"Creacion de cuenta - " + template.getNameSistema() + " AF-" + periodo.getAnioEscolar(),
						charreao, response.getEmail().toLowerCase(), false, null);
			}

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
//			UPDATE PERSONAL
			response = repoPersonal.save(dataPersonal);
//			UPDATE CUENTA USER
			Usuario user = new Usuario();
			Usuario exists = repoUser.findByCI(response.getNumDocumento().trim());
			String clave = "";
			if (exists == null) {
				clave = passwordEncoder.encode(response.getNumDocumento());
			} else {
				user.setIdUsuario(exists.getIdUsuario());
				clave = exists.getContrasenia();
			}

//				CREAR CUENTA DE USUARIO							
			user.setApellidos(response.getApellidos());
			user.setNombres(response.getNombre());
			user.setCI(response.getNumDocumento());
			user.setDireccion(response.getDireccion());
			user.setEstadoU("Activo");
			user.setUsuarioCorreo(response.getEmail().toLowerCase());
			user.setFechaNacimiento(response.getFechaNacimiento());
			user.setSexo(response.getSexo());
			user.setContrasenia(clave);

			user = repoUser.save(user);
			if (exists == null) {
				UsuarioPerfil perfilUser = new UsuarioPerfil();
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(4);
				perfilUser.setEstado("Activo");
				perfilUser.setFkUsuario(user);
				perfilUser.setFechaCreacionPerfil(new Date());
				perfilUser.setFkPerfil(perfil);
				repoPerfil.save(perfilUser);
			}
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

	@Override
	public List<Personal> onListarDataDashboardAlumno(int grado, int seccion, String nivel, int periodoescolar) {
		List<Personal> lista = new ArrayList<>();
		try {
//			PERIODO ESCOLAR
			PeriodoEscolar periodo = repoPeridoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
//			DATOS DEL ESTUDIANTE LOGUEADO
			Estudiante studentLogin = repoEstudiante.findByEmailEstudianteAndPeriodoEscolarIdPeriodoEscolar(
					ConstantApp.getuserLogin(), periodo.getIdPeriodoEscolar());
//			BUSCAR DATA SEGUN FILTROS
			lista = repoPersonal.findByNivelAcademicoAndLstGradoIdGradoAndLstSeccionIdSeccion(
					studentLogin.getNivelEscolar(), studentLogin.getGradoAlumno().getIdGrado(),
					studentLogin.getSeccionAlumno().getIdSeccion());
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

}
