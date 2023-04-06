package com.uisarel.institucion.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.Exceptions.ConflictException;
import com.uisarel.institucion.Exceptions.NotFoundException;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.modelo.repositorio.IEstudianteRepositori;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.modelo.repositorio.IUsuarioPerfilRepositorio;
import com.uisarel.institucion.modelo.repositorio.IUsuarioRepositorio;
import com.uisarel.institucion.servicio.IEstudianteService;
import com.uisarel.institucion.servicio.INotificacionesService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepositori repoEstudiante;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeridoEscolar;

	@Autowired
	private IUsuarioRepositorio repoUser;

	@Autowired
	private IUsuarioPerfilRepositorio repoPerfil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private INotificacionesService srvNotifications;

	@Override
	public List<Estudiante> onListarEstuandePeriodoEscolar(int periodoEscolar) {
		PeriodoEscolar periodo = repoPeridoEscolar.findByEstado("APERTURADO").get(0);
		

		return repoEstudiante.findByPeriodoEscolarIdPeriodoEscolar(periodo.getIdPeriodoEscolar());
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
//						GUARDAR ESTUDIANTE
						response = repoEstudiante.save(dataEstudiante);

						Usuario user = new Usuario();
						Usuario exists = repoUser.findByCI(response.getNumDocumento().trim());
						if (exists != null) {
							user.setIdUsuario(exists.getIdUsuario());
						}
//							CREAR CUENTA DE USUARIO							
						user.setApellidos(response.getApPaterno() + " " + response.getApMaterno());
						user.setNombres(response.getNombreEstudiante());
						user.setCI(response.getNumDocumento());
						user.setDireccion(response.getDireccionEstudiante());
						user.setEstadoU("Activo");
						user.setUsuarioCorreo(response.getEmailEstudiante().toLowerCase());
						user.setFechaNacimiento(response.getFnacimiento());
						user.setSexo(response.getSexoEstudiante());
						String claveString = response.getNumDocumento() + "AF-"
								+ validatePeriodo.get().getAnioEscolar();
						user.setContrasenia(passwordEncoder.encode(claveString));

						user = repoUser.save(user);
						if (exists == null) {
							UsuarioPerfil perfilUser = new UsuarioPerfil();
							Perfil perfil = new Perfil();
							perfil.setIdPerfil(2);
							perfilUser.setEstado("Activo");
							perfilUser.setFkUsuario(user);
							perfilUser.setFechaCreacionPerfil(new Date());
							perfilUser.setFkPerfil(perfil);
							repoPerfil.save(perfilUser);
//							srvNotifications.sendMensajeChasqui(
//									"CREACION DE CUENTA DE ACCESO AL SISTEMA ESCOLAR AF-2023",
//									"SE REMITE CONTRASEÑA DE ACCESO AL SISITEMA ESCOLAR SU CLAVE ES : " + claveString,
//									response.getEmailEstudiante(), false, "");
						}

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

//			UPDATE CUENTA USER
			Usuario user = new Usuario();
			Usuario exists = repoUser.findByCI(response.getNumDocumento().trim());
			String clave = "";
			if (exists == null) {
				clave = passwordEncoder.encode(response.getNumDocumento()) + "AF-"
						+ response.getPeriodoEscolar().getAnioEscolar();
			} else {
				user.setIdUsuario(exists.getIdUsuario());
				clave = exists.getContrasenia();
			}

//				CREAR CUENTA DE USUARIO							
			user.setApellidos(response.getApPaterno() + " " + response.getApMaterno());
			user.setNombres(response.getNombreEstudiante());
			user.setCI(response.getNumDocumento());
			user.setDireccion(response.getDireccionEstudiante());
			user.setEstadoU("Activo");
			user.setUsuarioCorreo(response.getEmailEstudiante().toLowerCase());
			user.setFechaNacimiento(response.getFnacimiento());
			user.setSexo(response.getSexoEstudiante());
			user.setContrasenia(clave);

			user = repoUser.save(user);
			if (exists == null) {
				UsuarioPerfil perfilUser = new UsuarioPerfil();
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(2);
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
	public void onEliminarEstudiantePeriodoId(int idEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estudiante> onListarEstuandePeriodoEscolarGradoSeccionNivel(int periodoEscolar, String nivelEscolar,
			int idGrado, int idSeccion) {
		List<Estudiante> lista = new ArrayList<>();
		try {
			PeriodoEscolar periodo = repoPeridoEscolar.findByEstado("APERTURADO").get(0);
			lista = repoEstudiante
					.findByPeriodoEscolarIdPeriodoEscolarAndNivelEscolarAndGradoAlumnoIdGradoAndSeccionAlumnoIdSeccion(
							periodo.getIdPeriodoEscolar(), nivelEscolar, idGrado, idSeccion);
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	@Override
	public Estudiante onBuscarEstudiantePeriodoEscolarId(int idEstudiante, int idperiodoEscolar) {
		Estudiante response = new Estudiante();
		try {
			response = repoEstudiante.findByPeriodoEscolarIdPeriodoEscolarAndIdEstudiante(idperiodoEscolar,
					idEstudiante);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
